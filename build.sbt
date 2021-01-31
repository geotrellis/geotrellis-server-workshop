import Dependencies._
import de.heikoseeberger.sbtheader._

name := "geotrellis-server-stac-workshop"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.12.12"
organization := "geotrellis"
scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-language:implicitConversions",
  "-language:reflectiveCalls",
  "-language:higherKinds",
  "-language:postfixOps",
  "-language:existentials",
  "-feature",
  "-Ypartial-unification"
)

shellPrompt := { s => Project.extract(s).currentProject.id + " > " }
run / fork := true
outputStrategy := Some(StdoutOutput)

headerLicense := Some(HeaderLicense.ALv2(java.time.Year.now.getValue.toString, "Azavea"))
headerMappings := Map(
  FileType.scala -> CommentStyle.cStyleBlockComment.copy(
  commentCreator = { (text, existingText) => {
    // preserve year of old headers
    val newText = CommentStyle.cStyleBlockComment.commentCreator.apply(text, existingText)
    existingText.flatMap(_ => existingText.map(_.trim)).getOrElse(newText)
  } })
)

assembly / test := {}
sources in (Compile, doc) := (sources in (Compile, doc)).value
assembly / assemblyMergeStrategy := {
  case "reference.conf"   => MergeStrategy.concat
  case "application.conf" => MergeStrategy.concat
  case PathList("META-INF", xs @ _*) =>
    xs match {
      case ("MANIFEST.MF" :: Nil) =>
        MergeStrategy.discard
      case ("services" :: _ :: Nil) =>
        MergeStrategy.concat
      case ("javax.media.jai.registryFile.jai" :: Nil) | ("registryFile.jai" :: Nil) | ("registryFile.jaiext" :: Nil) =>
        MergeStrategy.concat
      case (name :: Nil) if name.endsWith(".RSA") || name.endsWith(".DSA") || name.endsWith(".SF") =>
        MergeStrategy.discard
      case _ =>
        MergeStrategy.first
    }
  case _ => MergeStrategy.first
}

Global / cancelable := true

resolvers ++= Seq(
  Resolver.bintrayRepo("azavea", "geotrellis"),
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  "osgeo-snapshots" at "https://repo.osgeo.org/repository/snapshot/",
  "osgeo-releases" at "https://repo.osgeo.org/repository/release/",
  "eclipse-releases" at "https://repo.eclipse.org/content/groups/releases",
  "eclipse-snapshots" at "https://repo.eclipse.org/content/groups/snapshots"
)

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.3" cross CrossVersion.full)
addCompilerPlugin("org.scalamacros" %% "paradise" % "2.1.1" cross CrossVersion.full)

libraryDependencies ++= Seq(
  geotrellisS3,
  geotrellisGdal,
  geotrellisServerOgc,
  http4sDsl.value,
  http4sBlazeServer.value,
  http4sBlazeClient.value,
  http4sCirce.value,
  http4sXml.value,
  cats.value,
  catsEffect.value,
  logback % Runtime,
  pureConfig,
  pureConfigCatsEffect,
  scaffeine,
  decline,
  stac4s,
  ansiColors212 % Provided,
  scalatest % Test
)

excludeDependencies ++= Seq(
  // log4j brought in via uzaygezen is a pain for us
  ExclusionRule("log4j", "log4j"),
  ExclusionRule("org.slf4j", "slf4j-log4j12"),
  ExclusionRule("org.slf4j", "slf4j-nop")
)
