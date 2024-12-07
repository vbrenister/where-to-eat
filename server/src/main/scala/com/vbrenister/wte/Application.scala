package com.vbrenister.wte

import zio.*
import com.vbrenister.wte.domain.User
import com.vbrenister.wte.http.api.Endpoints
import zio.http.Server
import com.vbrenister.wte.services.UserService

object Application extends ZIOAppDefault:

  def run = Server
    .serve(Endpoints.routes)
    .provide(Server.live, ZLayer.succeed(Server.Config.default.port(8080)), UserService.live)
