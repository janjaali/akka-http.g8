name := "$project_name$"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.6"

assemblyOutputPath in assembly := file("target/$project_name$.jar")

val dependencies = Seq(
  "com.typesafe.akka" %% "akka-http" % "10.0.9",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.10",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.0.9" % Test,

  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

libraryDependencies ++= dependencies
