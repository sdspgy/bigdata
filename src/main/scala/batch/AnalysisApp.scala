package batch

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.CellUtil
import org.apache.hadoop.hbase.client.{Result, Scan}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.protobuf.ProtobufUtil
import org.apache.hadoop.hbase.util.{Base64, Bytes}
import org.apache.spark.sql.SparkSession

object AnalysisApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .appName("AnalysisApp").master("local[2]").getOrCreate()

    // 获取要进行统计分析的日期
    val day = "20190130"

    // 连接HBase
    val conf = new Configuration()
    conf.set("hbase.rootdir", "hdfs://mvptyz.cn:8020/hbase")
    conf.set("hbase.zookeeper.quorum", "mvptyz.cn:2181")

    val tableName = "pk_hbase_java_api"
    conf.set(TableInputFormat.INPUT_TABLE, tableName) // 要从哪个表里面去读取数据

    val scan = new Scan()

    // 设置要查询的cf
    scan.addFamily(Bytes.toBytes("info"))

    // 设置要查询的列
    scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("age"))
    scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("birthday"))

    scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("company"))

    // 设置Scan
    conf.set(TableInputFormat.SCAN, Base64.encodeBytes(ProtobufUtil.toScan(scan).toByteArray))

    // 通过Spark的newAPIHadoopRDD读取数据
    val hbaseRDD = spark.sparkContext.newAPIHadoopRDD(conf,
      classOf[TableInputFormat],
      classOf[ImmutableBytesWritable],
      classOf[Result]
    )

    hbaseRDD.take(2).foreach(x => {
      val rowKey = Bytes.toString(x._1.get())

      for (cell <- x._2.rawCells()) {
        val cf = Bytes.toString(CellUtil.cloneFamily(cell))
        val qualifier = Bytes.toString(CellUtil.cloneQualifier(cell))
        val value = Bytes.toString(CellUtil.cloneValue(cell))

        println(s"$rowKey : $cf : $qualifier : $value")
      }
    })

    spark.stop()
  }
}
