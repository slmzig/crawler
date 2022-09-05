ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "crawler"
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.15",
  "com.typesafe.akka" %% "akka-stream" % "2.5.31",
  "org.jsoup" % "jsoup" % "1.15.3",
  "org.scalatest" %% "scalatest" % "3.2.13" % "test",
  "com.softwaremill.macwire" %% "macros" % "2.5.0" % Provided,
  "com.softwaremill.macwire" %% "macrosakka" % "2.5.0" % Provided,
  "com.softwaremill.macwire" %% "util" % "2.5.0",
  "de.heikoseeberger" %% "akka-http-play-json" % "1.38.2",
)