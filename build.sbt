import sbt.Project.projectToRef

ThisBuild / version := "0.1.0-SNAPSHOT"

Global / onChangedBuildSource := ReloadOnSourceChanges
Global / excludeLintKeys      := Set(idePackagePrefix)

lazy val scala3Version = "3.3.1"

lazy val commonSettings = Seq(
  version      := "0.0.1-SNAPSHOT",
  organization := "com.example",
  scalaVersion := scala3Version,
  libraryDependencies ++= Seq(
    dependencies.CatsEffect.effect,
    dependencies.Weaver.weaver
  ),
  testFrameworks += new TestFramework("weaver.framework.CatsEffect")
)

lazy val root = (project in file("."))
    .settings(commonSettings*)
    .settings(
      name             := "app",
      idePackagePrefix := Some("com.example.app")
    )
    .dependsOn(backend, frontend)
    .aggregate(backend, frontend, common)

lazy val common = (project in file("app/common"))
    .settings(commonSettings*)
    .settings(
      name := "common",
      libraryDependencies ++= Seq(
        dependencies.Smithy4s.smithy4s,
        dependencies.Smithy4s.smithy4sSwagger
      )
    )
    .enablePlugins(Smithy4sCodegenPlugin)

lazy val backend = (project in file("app/backend"))
    .settings(commonSettings*)
    .settings(
      name             := "api",
      idePackagePrefix := Some("com.example.app.api"),
      libraryDependencies ++= Seq(
        dependencies.Http4s.server,
        dependencies.Http4s.client,
        dependencies.Http4s.dsl,
        dependencies.Natchez.core,
        dependencies.Scribe.core,
        dependencies.Scribe.cats,
        dependencies.Skunk.skunk,
        dependencies.Tyrian.tyrian
      )
    )
    .dependsOn(common)

lazy val frontend = (project in file("app/frontend"))
    .settings(commonSettings*)
    .settings(
      name                            := "ui",
      idePackagePrefix                := Some("com.example.app.ui"),
      libraryDependencies ++= Seq(
        "io.indigoengine" %%% "tyrian-io" % dependencies.Tyrian.tyrianVersion
      ),
      scalaJSUseMainModuleInitializer := true,
      scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) }
    )
    .enablePlugins(ScalaJSPlugin)
    .dependsOn(common)

addCommandAlias(
  "start",
  List(
    "clean",
    "common/compile",
    // "spa/fastOptJS::webpack",
    "frontend/fullLinkJS",
    "run"
  ).mkString(";", ";", "")
)

addCommandAlias(
  "buildAll",
  List(
    // "spa/fastOptJS::webpack",
    "frontend/fullLinkJS",
    "backend/test"
  ).mkString(";", ";", "")
)
