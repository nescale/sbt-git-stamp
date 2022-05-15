val pluginName = "sbt-git-stamp"

lazy val root = Project(pluginName, base = file("."))
  .settings(
    sbtPlugin := true,
    organization := "com.nescale",
    scalaVersion := "2.12.15",
    crossSbtVersions := Vector("0.13.18", "1.6.2"),
    libraryDependencies ++=
      Seq(
        "org.eclipse.jgit" % "org.eclipse.jgit" % "6.1.0.202203080745-r",
        "org.scalatest"   %% "scalatest"        % "3.2.12" % Test
      )
  )
