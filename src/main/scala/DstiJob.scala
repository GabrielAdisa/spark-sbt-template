import org.apache.spark.sql.{Dataset, SparkSession}

case class AccessLog(
    ip: String,
    ident: String,
    user: String,
    datetime: String,
    request: String,
    status: String,
    size: String,
    referer: String,
    userAgent: String,
    unk: String
)

object DstiJob {

  def run(spark: SparkSession): Unit = {
    val path = "src/resources/access.log.gz"
    val outputPath = "src/resources/jsonreport.json"

    createReport(spark: SparkSession, path: String, outputPath: String)
  }

  def toAccessLog(params: List[String]) = AccessLog(
    params(0),
    params(1),
    params(2),
    params(3),
    params(4),
    params(5),
    params(6),
    params(7),
    params(8),
    params(9)
  )

  def createReport(
      spark: SparkSession,
      gzPath: String,
      outputPath: String
  ): Unit = {
    val logs = spark.read.text(gzPath)
    val RegEx =
      """^(?<ip>[0-9.]+) (?<identd>[^ ]) (?<user>[^ ]) \[(?<datetime>[^\]]+)\] \"(?<request>[^\"]*)\" (?<status>[^ ]*) (?<size>[^ ]*) \"(?<referer>[^\"]*)\" \"(?<useragent>[^\"]*)\" \"(?<unk>[^\"]*)\"""".r

    println(s"logs: ${logs.take(5).drop(1).head}")
    /*  val logAsString = logs.map() //logs.map(x => x.getString(0))
    val dsParsed = logAsString.flatMap(x => R.unapplySeq(x))

    val ds = dsParsed.map(toAccessLog _)
    val dsWithTime = ds.withColumn(
      "datetime",
      to_timestamp(ds("datetime"), "dd/MMM/yyyy:HH:mm:ss X")

    )*/
  }
}
