package com.example.app.api
package services

import cats.Applicative
import cats.effect.{Concurrent, Resource}
import com.example.app.health.{HealthCheckOutput, HealthService}
import org.http4s.HttpRoutes
import smithy4s.http4s.SimpleRestJsonBuilder

class HealthServiceLive[F[_]: Applicative] private extends HealthService[F]:

    override def healthCheck(): F[HealthCheckOutput] =
        Applicative[F].pure(HealthCheckOutput())

end HealthServiceLive

object HealthServiceLive:

    def apply[F[_]: Concurrent]: Resource[F, HttpRoutes[F]] =
        SimpleRestJsonBuilder.routes(new HealthServiceLive[F]).resource

end HealthServiceLive
