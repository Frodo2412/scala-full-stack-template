package com.example.app.ui
package health

import Msg.{GetStatus, NetworkError}

import cats.effect.IO
import tyrian.Cmd
import tyrian.http.{Decoder, Http, Request}

object HealthService:

    def checkStatus: Cmd[IO, Msg] = Http.send(
      Request.get("/api/health"),
      Decoder[Msg](
        res => GetStatus(res.status.code),
        err => NetworkError(err.toString)
      )
    )

end HealthService
