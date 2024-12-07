package com.vbrenister.wte.services

import zio.ZIO
import zio.stream.ZStream
import com.vbrenister.wte.domain.User
import zio.ZLayer

trait UserService {
    def getAll(): ZIO[Any, Nothing, List[User]]
}

class UserServiceLive extends UserService{

  override def getAll(): ZIO[Any, Nothing, List[User]] = 
    ZIO.succeed((1 to 10).map(a => User(a, s"User $a")).toList)
}


object UserService {
    val live = ZLayer.succeed(new UserServiceLive)


    def getAll():  ZIO[UserService, Nothing, List[User]] = ZIO.serviceWithZIO[UserService](_.getAll()) }