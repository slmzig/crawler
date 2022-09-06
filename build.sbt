ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val akkaHttpVersion = "10.2.9"
lazy val akkaVersion    = "2.6.19"

lazy val root = (project in file("."))
  .settings(
    name := "crawler"
  )
  .enablePlugins(JavaAppPackaging)

libraryDependencies ++= Seq(
  "org.jsoup" % "jsoup" % "1.15.3",
  "org.scalatest" %% "scalatest" % "3.2.13" % "test",
  "com.softwaremill.macwire" %% "macros" % "2.5.0" % Provided,
  "com.softwaremill.macwire" %% "macrosakka" % "2.5.0" % Provided,
  "com.softwaremill.macwire" %% "util" % "2.5.0",
  "de.heikoseeberger" %% "akka-http-play-json" % "1.38.2",
  "org.scalamock" %% "scalamock" % "5.1.0" % Test,
  "com.typesafe.akka" %% "akka-http"                % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json"     % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor-typed"         % akkaVersion,
  "com.typesafe.akka" %% "akka-stream"              % akkaVersion,
  "ch.qos.logback"    % "logback-classic"           % "1.2.3",
  "com.typesafe.akka" %% "akka-http-testkit"        % akkaHttpVersion % Test,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion     % Test,
  "org.scalatest"     %% "scalatest"                % "3.1.4"         % Test
)