package com.vbrenister.wte.http.api

import com.vbrenister.wte.domain.User
import com.vbrenister.wte.services.UserService
import scala.concurrent.Future
import sttp.tapir.generic.auto.*
import sttp.tapir.json.zio.*
import sttp.tapir.server.ziohttp.ZioHttpInterpreter
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import sttp.tapir.ztapir.*
import sttp.tapir.Endpoint
import sttp.tapir.PublicEndpoint
import zio.*
import zio.http.Header.AccessControlAllowOrigin
import zio.http.Middleware
import zio.http.Middleware.cors
import zio.http.Middleware.CorsConfig
import zio.http.Response
import zio.http.Routes
import zio.json.DeriveJsonCodec
import zio.json.JsonCodec

object Endpoints extends TapirJsonZio {

  implicit val userCodec: JsonCodec[User] = DeriveJsonCodec.gen[User]

  val config: CorsConfig =
    CorsConfig(
      allowedOrigin = _ => Some(AccessControlAllowOrigin.All)
    )

  val pingEndpoint: Endpoint[Unit, Unit, Unit, List[User], Any] = endpoint.get
    .in("ping")
    .out(jsonBody[List[User]])

  val ping: ZServerEndpoint[UserService, Any] =
    pingEndpoint.zServerLogic[UserService] { _ =>
      UserService.getAll()
    }

  val swaggerEndpoints =
    SwaggerInterpreter()
      .fromEndpoints[Task](List(pingEndpoint), "Our pets", "1.0")
      .map(_.widen[UserService])

  val all: List[ZServerEndpoint[UserService, Any]] = List(ping)

  val routes: Routes[UserService, Response] =
    ZioHttpInterpreter().toHttp(all ++ swaggerEndpoints) @@ Middleware
      .requestLogging() @@ cors(config)

}
