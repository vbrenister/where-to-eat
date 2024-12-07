import sbt.*
import Keys.*

object Dependencies {

  lazy val zio: ModuleID = "dev.zio" %% "zio" % Versions.zio

  lazy val serverDependencies: Seq[ModuleID] = Seq(zio)

}
