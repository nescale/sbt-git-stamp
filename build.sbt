inThisBuild(List(
  organization := "com.nescale",
  homepage := Some(url("https://github.com/nescale/sbt-git-stamp")),
  licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
  developers := List(
    Developer(
      "falconepl",
      "Przemysław Sokół",
      "falconepl+dev@nescale.com",
      url("https://nescale.com/falconepl")
    )
  )
))

sonatypeCredentialHost := "s01.oss.sonatype.org"
sonatypeRepository := "https://s01.oss.sonatype.org/service/local"

ThisBuild / githubWorkflowTargetTags ++= Seq("v*")
ThisBuild / githubWorkflowPublishTargetBranches :=
  List(RefPredicate.StartsWith(Ref.Tag("v")), RefPredicate.Equals(Ref.Branch("main")))

ThisBuild / githubWorkflowPublish := Seq(WorkflowStep.Sbt(List("ci-release")))

ThisBuild / githubWorkflowEnv ++= List(
  "PGP_PASSPHRASE",
  "PGP_SECRET",
  "SONATYPE_PASSWORD",
  "SONATYPE_USERNAME"
).map { envKey =>
  envKey -> s"$${{ secrets.$envKey }}"
}.toMap

val pluginName = "sbt-git-stamp"

lazy val root = Project(pluginName, base = file("."))
  .settings(
    sbtPlugin := true,
    scalaVersion := "2.12.15",
    crossSbtVersions := Vector("0.13.18", "1.6.2"),
    libraryDependencies ++=
      Seq(
        "org.eclipse.jgit" % "org.eclipse.jgit" % "6.1.0.202203080745-r",
        "org.scalatest"   %% "scalatest"        % "3.2.12" % Test
      )
  )
