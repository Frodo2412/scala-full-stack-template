package com.example.app.api

import cats.effect.kernel.Sync
import fs2.io.file.Files
import org.http4s.dsl.Http4sDsl
import org.http4s.headers.`Content-Type`
import org.http4s.{HttpRoutes, MediaType, StaticFile}
import tyrian.Tyrian

class Routes[F[_]: Sync: Files] extends Http4sDsl[F]:

  val home: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> Root                      =>
      Ok(
        Tyrian.render(true, HomePage.page),
        `Content-Type`(MediaType.text.html)
      )
    case request @ GET -> Root / "opt.js" =>
      val spa = fs2.io.file.Path(
        "./app"
      ) / "frontend" / "target" / "scala-3.3.1" / "ui-opt" / "main.js"
      StaticFile
        .fromPath(spa.absolute, Some(request))
        .getOrElseF(NotFound(spa.absolute.toString))
  }

end Routes
