ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val akkaHttpVersion = "10.2.9"
lazy val akkaVersion = "2.6.19"

lazy val root = (project in file("."))
  .settings(
    name := "crawler",
    libraryDependencies ++= Dependencies.libraries
  )
  .enablePlugins(JavaAppPackaging)
