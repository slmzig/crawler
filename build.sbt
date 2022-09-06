import com.typesafe.sbt.packager.docker._
ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(GitVersioning)
  .settings(
    name := "crawler",
    scalaVersion := "2.13.6",
    libraryDependencies ++= Dependencies.libraries,
    git.useGitDescribe := true,
    git.baseVersion := "0.0.0",
    git.gitTagToVersionNumber := { tag: String =>
      if (tag matches "([0-9]+\\\\.[0-9]+\\\\.[0-9]+)") Some(tag)
      else None
    }
  )
dockerBaseImage := "openjdk:8-jre-alpine"
dockerCommands := dockerCommands.value.map {
  case ExecCmd("CMD", _ @_*) =>
    ExecCmd("CMD", "/opt/docker/bin/crawler")
  case other =>
    other
}
dockerCommands ++= Seq(Cmd("USER", "root"), ExecCmd("RUN", "apk", "add", "--no-cache", "bash"))
dockerLabels := Map(version.value -> version.value, "latest" -> version.value)
dockerUpdateLatest := true
