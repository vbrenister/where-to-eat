import sbt.*
import Keys.*

object Dependencies {

  lazy val zio: ModuleID = "dev.zio" %% "zio" % Versions.zio

  lazy val tapirCore: ModuleID = "com.softwaremill.sttp.tapir" %% "tapir-core" % Versions.tapir

  lazy val tapirZio: ModuleID = "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server" % Versions.tapir

  lazy val zioJsonTapir: ModuleID = "com.softwaremill.sttp.tapir" %% "tapir-json-zio" % Versions.tapir

  lazy val tapirSwagger: ModuleID = "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % Versions.tapir

  lazy val serverDependencies: Seq[ModuleID] = Seq(zio, tapirCore, tapirZio, zioJsonTapir, tapirSwagger)

}
