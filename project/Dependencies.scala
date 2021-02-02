import sbt._
import sbt.Keys._

object Dependencies {
  def catsVersion(module: String) = Def.setting {
    module match {
      case "core"   => "org.typelevel" %% s"cats-$module" % "2.1.1"
      case "effect" => "org.typelevel" %% s"cats-$module" % "2.1.3"
    }
  }

  def circeVersion(module: String) = Def.setting { "io.circe" %% s"circe-$module" % "0.13.0" }
  def http4sVer(module: String)    = Def.setting { "org.http4s" %% s"http4s-$module" % "0.21.7" }

  val cats                = catsVersion("core")
  val catsEffect          = catsVersion("effect")
  val scaffeine           = "com.github.blemale"                    %% "scaffeine"                   % "2.6.0"
  val concHashMap         = "com.googlecode.concurrentlinkedhashmap" % "concurrentlinkedhashmap-lru" % "1.4.2"
  val geotrellisS3        = "org.locationtech.geotrellis"           %% "geotrellis-s3"               % "3.5.2"
  val geotrellisGdal      = "org.locationtech.geotrellis"           %% "geotrellis-gdal"             % "3.5.2"
  val geotrellisServerOgc = "com.azavea.geotrellis"                 %% "geotrellis-server-ogc"       % "4.2.0-18-ge2d9972-SNAPSHOT"

  val decline           = "com.monovore" %% "decline" % "1.0.0"
  val http4sBlazeClient = http4sVer("blaze-client")
  val http4sBlazeServer = http4sVer("blaze-server")
  val http4sCirce       = http4sVer("circe")
  val http4sDsl         = http4sVer("dsl")
  val http4sXml         = http4sVer("scala-xml")

  val pureConfig           = "com.github.pureconfig" %% "pureconfig"             % "0.12.3"
  val pureConfigCatsEffect = "com.github.pureconfig" %% "pureconfig-cats-effect" % "0.12.2"
  val scalatest            = "org.scalatest"         %% "scalatest"              % "3.1.1"

  val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"

  val stac4s = "com.azavea.stac4s" %% "core" % "0.0.14"

  val ansiColors212 = "org.backuity" %% "ansi-interpolator" % "1.1.0"
}
