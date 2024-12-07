package com.vbrenister.wte

import com.vbrenister.wte.domain.User
import com.vbrenister.wte.http.api.Endpoints
import com.vbrenister.wte.services.UserService
import zio.*
import zio.http.Server

object Application extends ZIOAppDefault:

  def run = Server
    .serve(Endpoints.routes)
    .provide(
      Server.live,
      ZLayer.succeed(Server.Config.default.port(8080)),
      UserService.live
    )
