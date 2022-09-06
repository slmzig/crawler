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
