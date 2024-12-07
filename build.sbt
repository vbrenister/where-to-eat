import org.scalajs.linker.interface.ModuleSplitStyle

ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := Versions.scala

lazy val root =
  project
    .in(file("."))
    .aggregate(client, server)
    .settings(
      name := "Where to Eat"
    )

lazy val server = project
  .in(file("./server"))
  .settings(
    libraryDependencies ++= Dependencies.serverDependencies
  )
  .dependsOn(shared.jvm)

lazy val client = project
  .in(file("./client"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    libraryDependencies ++= List(
      "com.raquo"    %%% "laminar"     % Versions.laminar,
      "org.scala-js" %%% "scalajs-dom" % Versions.scalajsDom
    ),
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
    },
    scalaJSUseMainModuleInitializer := true
  )
  .dependsOn(shared.js)

lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .in(file("."))
