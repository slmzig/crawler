import sbt._

object Dependencies {
  private object versions {
    lazy val akkaHttp = "10.2.9"
    lazy val akka = "2.6.19"
    lazy val jsoup = "1.15.3"
    lazy val scalatest = "3.2.13"
    lazy val macwire = "2.5.0"
    lazy val playJson = "1.38.2"
    lazy val scalaMock = "5.1.0"
    lazy val logBack = "1.2.3"
  }

  val libraries: Seq[ModuleID] = Seq(
    "org.jsoup" % "jsoup" % versions.jsoup,
    "org.scalatest" %% "scalatest" % versions.scalatest % Test,
    "com.softwaremill.macwire" %% "macros" % versions.macwire % Provided,
    "com.softwaremill.macwire" %% "macrosakka" % versions.macwire % Provided,
    "com.softwaremill.macwire" %% "util" % versions.macwire,
    "de.heikoseeberger" %% "akka-http-play-json" % versions.playJson,
    "org.scalamock" %% "scalamock" % versions.scalaMock % Test,
    "com.typesafe.akka" %% "akka-http" % versions.akkaHttp,
    "com.typesafe.akka" %% "akka-http-spray-json" % versions.akkaHttp,
    "com.typesafe.akka" %% "akka-actor-typed" % versions.akka,
    "com.typesafe.akka" %% "akka-stream" % versions.akka,
    "ch.qos.logback" % "logback-classic" % versions.logBack,
    "com.typesafe.akka" %% "akka-http-testkit" % versions.akkaHttp % Test,
    "com.typesafe.akka" %% "akka-actor-testkit-typed" % versions.akka % Test,
  )
}

