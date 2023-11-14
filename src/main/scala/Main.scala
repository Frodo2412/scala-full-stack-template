package com.example.app

import api.Server

import cats.effect.{IO, Resource, ResourceApp}

object Main extends ResourceApp.Forever:

  override def run(args: List[String]): Resource[IO, Unit] =
    for _ <- Server.build[IO]
    yield ()

end Main
