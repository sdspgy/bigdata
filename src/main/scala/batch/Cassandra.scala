package batch

import org.apache.spark.sql.SparkSession

object Cassandra {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("cassandra")
      .master("local[2]")
      .config("spark.cassandra.connection.host", "10.2.10.41")
      .getOrCreate()
    val dfFormCassandra = spark.read
      .format("org.apache.spark.sql.cassandra")
      .option("keyspace","meng")
      .option("table","student")
      .load()
    //显示五行Cassandra数据
    dfFormCassandra.show(1,false)
    spark.stop()

  }
}
