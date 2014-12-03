import sbt._
import Keys._


object BuildSettings {
  val buildOrganization = "xyz.unicornfarts"
  val buildVersion      = "1.0"
  val buildScalaVersion = "2.11.4"
  val buildExportJars = true

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization := buildOrganization,
    version      := buildVersion,
    scalaVersion := buildScalaVersion,
    exportJars   := buildExportJars,
    testOptions in Test := Seq(
      Tests.Setup( () => System.setProperty("scala.env", System.getProperty("scala.env", "test")): Unit )
    )
  )
}

object Resolvers {
  val sonatypeResolvers = Seq (Resolver.sonatypeRepo("public"))
}

object Dependencies {
  // Only here for quick console use
  //val redis = "net.debasishg" % "redisclient_2.9.2" % "2.10" // off
  //val commons_pool2 = "org.apache.commons" % "commons-pool2" % "2.0"
  //val jackson_core = "com.fasterxml.jackson.core" % "jackson-core" % "2.3.1"
  //val jackson_databind = "com.fasterxml.jackson.core" % "jackson-databind" % "2.3.1"
  //val hamcrest_all = "org.hamcrest" % "hamcrest-all" % "1.3" % "test"
  //val junit = "junit" % "junit" % "${junit.version}" % "test"
  //val jmock_junit4 = "org.jmock" % "jmock-junit4" % "2.6.0" % "test"
  //val jmock_legacy = "org.jmock" % "jmock-legacy" % "2.6.0" % "test"

  // Logging
  val logbackCore    = "ch.qos.logback" % "logback-core" % "1.1.1" // logger
  val logbackClassic = "ch.qos.logback" % "logback-classic" % "1.1.1" // logger
  val logbackGelf = "me.moocar" % "logback-gelf" % "0.10p2"

  // Config
  val config = "com.typesafe" % "config" % "0.4.0"

  val scalaUtils = "org.scalautils" % "scalautils_2.11" % "2.1.5"

  // Including these in the actual project (not just the test configuration)
  // so that other px projects that already have a dependency on scala-utils
  // can use the same testing base class(es) instead of duplicating that code
  val scalaTest = "org.scalatest" %% "scalatest" % "2.2.1"
  val mockito = "org.mockito" % "mockito-core" % "1.9.5"

}

object build extends sbt.Build {

  import BuildSettings._
  import Resolvers._
  import Dependencies._

  val projectDependencies = Seq(
    logbackCore, logbackClassic, config
  )

  val projectSettings = buildSettings ++ Seq(libraryDependencies ++= projectDependencies ) ++ Seq(resolvers := sonatypeResolvers)

  lazy val project= Project("dynamo-zagreb", file("."), settings = projectSettings)

}
