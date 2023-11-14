package com.example.app.api

import services.HealthServiceLive

import cats.effect.{Async, Resource}
import cats.syntax.all.*
import com.comcast.ip4s.{ipv4, port}
import fs2.io.file.Files
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Server
import org.http4s.server.middleware.Logger

object Server:

    def build[F[_]: Async: Files]: Resource[F, Server] =
        for {

            httpApp <- Resource pure Routes[F].home

            health <- HealthServiceLive[F]

            finalHttpApp =
                Logger.httpApp(true, true)((httpApp <+> health).orNotFound)

            exitCode <- EmberServerBuilder
                            .default[F]
                            .withHost(ipv4"0.0.0.0")
                            .withPort(port"8080")
                            .withHttpApp(finalHttpApp)
                            .build
        } yield exitCode

end Server
