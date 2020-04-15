package batch

import org.apache.spark.sql.SparkSession

object Demo {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("ImoocLogApp").master("local[2]").getOrCreate()
    System.setProperty("icode", "7422FEA800522889")
    var logDF = spark.read.format("com.imooc.bigdata.spark.pk").option("path", "/Volumes/pgy/space/bigData/src/data/test-access.log")
      .load()
    logDF.show()
    spark.stop()
  }
}
