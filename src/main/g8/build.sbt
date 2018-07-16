name := "$project_name$"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.6"

assemblyOutputPath in assembly := file("target/$project_name$.jar")

val akkaHttpVersion = "10.1.3"

val dependencies = Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,

  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

libraryDependencies ++= dependencies
