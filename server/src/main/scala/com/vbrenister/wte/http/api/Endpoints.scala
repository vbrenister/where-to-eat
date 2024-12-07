package com.vbrenister.wte.http.api
import sttp.tapir.PublicEndpoint
import sttp.tapir.ztapir.*
import zio.*
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.ziohttp.ZioHttpInterpreter
import com.vbrenister.wte.services.UserService
import sttp.tapir.json.zio.*
import com.vbrenister.wte.domain.User
import zio.json.DeriveJsonCodec
import zio.json.JsonCodec
import sttp.tapir.generic.auto.*
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import scala.concurrent.Future
import sttp.tapir.Endpoint
import zio.http.Routes
import zio.http.Response

object Endpoints extends TapirJsonZio {

  implicit val userCodec: JsonCodec[User] = DeriveJsonCodec.gen[User]

  val pingEndpoint: Endpoint[Unit, Unit, Unit, List[User], Any] = endpoint.get
    .in("ping")
    .out(jsonBody[List[User]])

  val ping: ZServerEndpoint[UserService, Any] =
    pingEndpoint.zServerLogic[UserService] { _ =>
      UserService.getAll()
    }

  val swaggerEndpoints =
    SwaggerInterpreter()
      .fromEndpoints[Task](List(pingEndpoint), "Our pets", "1.0").map(_.widen[UserService])

  val all: List[ZServerEndpoint[UserService, Any]] = List(ping)

  
  val routes: Routes[UserService, Response] = 
    ZioHttpInterpreter().toHttp(all ++ swaggerEndpoints)

}
