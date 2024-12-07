package com.vbrenister.wte

import zio.*
import com.vbrenister.wte.domain.User

object Application extends ZIOAppDefault:

  val user = User(1, "victr")
  def run = ZIO.logInfo(s"Where to Eat? $user")
