import _root_.sbtassembly.Plugin.AssemblyKeys._
import _root_.sbtassembly.Plugin._
import sbt._
import Keys._
import com.typesafe.sbt.SbtSite.site
import sbtbuildinfo.Plugin._

object enjoyCityBuild extends Build {
  lazy val project = Project(id = "enjoycity", base = file("."), settings = Seq(
    sbtPlugin := false,
    organization := "com.enjoycity",
    version := "0.1-SNAPSHOT"
  ) ++
    Defaults.defaultSettings ++
    assemblySettings ++
    compilerSettings ++
    buildInfoSettings ++
    runSettings++
    dependencies
  )

  def compilerSettings = Seq(
    scalaVersion := "2.11.2",
    crossScalaVersions := Seq("2.11.1", "2.10.4"),
    javacOptions in Compile ++= Seq("-encoding", "utf8", "-g"),
    scalacOptions ++= Seq("-deprecation", "-unchecked", "-encoding", "utf8"),

    // Main class
    mainClass in (Compile,run) := Some("com.enjoycity.Main")
  )

  def dependencies = Seq(
    resolvers += "spray repo" at "http://repo.spray.io",
    resolvers += "spray nightlies repo" at "http://nightlies.spray.io",
    libraryDependencies += "org.mongodb" %% "casbah" % "2.7.1",
    libraryDependencies += "com.novus" %% "salat-core" % "1.9.8" withSources(),
    libraryDependencies += "junit" % "junit" % "4.11" % "test" withSources(),
    libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.1" % "test" withSources(),
    libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.4" withSources(),
    libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.3.4" withSources(),
    libraryDependencies += "com.typesafe.akka" %% "akka-cluster" % "2.3.3" withSources(),
    libraryDependencies += "com.typesafe.akka" %% "akka-contrib" % "2.3.3" withSources(),
    libraryDependencies += "org.scalastuff" %% "json-parser" % "2.0.2" withSources(),
    libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.0.1" withSources(),
    libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.0.1" withSources(),
    libraryDependencies += "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.0" withSources(),
    libraryDependencies += "joda-time" % "joda-time" % "2.3" withSources(),
    libraryDependencies += "org.joda" % "joda-convert" % "1.6" withSources(), // for class file error in joda-time
    libraryDependencies += "com.google.guava" % "guava" % "17.0" withSources(),
    libraryDependencies += "com.typesafe" % "config" % "1.2.1" withSources(),
    libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",

    libraryDependencies <++= scalaBinaryVersion {
      case "2.11" => Seq(
        "io.spray" %% "spray-json" % "1.2.6" withSources(),
        "io.spray" %% "spray-can" % "1.3.1" withSources(),
        "io.spray" %% "spray-client" % "1.3.1" withSources(),
        "io.spray" %% "spray-servlet" % "1.3.1" withSources(),
        "io.spray" %% "spray-routing" % "1.3.1" withSources(),
        "io.spray" %% "spray-caching" % "1.3.1" withSources(),
        "io.spray" %% "spray-testkit" % "1.3.1" % "test" withSources())
      case _ => Seq(
        "io.spray" %% "spray-json" % "1.2.6" withSources(),
        "io.spray" % "spray-can" % "1.3.1" withSources(),
        "io.spray" % "spray-client" % "1.3.1" withSources(),
        "io.spray" % "spray-servlet" % "1.3.1" withSources(),
        "io.spray" % "spray-routing" % "1.3.1" withSources(),
        "io.spray" % "spray-caching" % "1.3.1" withSources(),
        "io.spray" % "spray-testkit" % "1.3.1" % "test" withSources())
    }
  )

  def buildInfoSettings = sbtbuildinfo.Plugin.buildInfoSettings ++ Seq(

    // Package in which to create BuildInfo
    buildInfoPackage := "com.enjoycity",

    // Default build info keys
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion,

      // Add the build timestamp
      BuildInfoKey.action("buildTime")(System.currentTimeMillis)),

    // Call build info on each compile
    sourceGenerators in Compile <+= buildInfo,

    // Main class
    mainClass in (Compile,packageBin) := Some("com.enjoycity.Main")
  )

  def assemblySettings = baseAssemblySettings ++ Seq(
    // Skip tests during assembly
    test in assembly := {},

    // Override to remove the default '-assembly' infix
    jarName in assembly <<= (name, version) map { (name, version) => name + "-" + version + ".jar" },

    // Main class
    mainClass in assembly := Some("com.enjoycity.Main")
  )

  def runSettings = Seq(
    // Run in devmode when run from SBT
    javaOptions in run += "-Ddevmode=true",

    // Need to fork to activate run-specific javaOptions
    fork in run := true,

    //this is so that index deletion does not interfere with each other.
    parallelExecution in Test:= false,

    // Main class
    mainClass in (Compile,run) := Some("com.enjoycity.Main")
  )

}