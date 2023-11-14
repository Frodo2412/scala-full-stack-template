package com.example.app.ui

import cats.effect.kernel.Resource
import cats.effect.{Concurrent, Resource}
import com.example.app.health.HealthService
import org.http4s.Uri
import org.http4s.client.Client
import smithy4s.http4s.SimpleRestJsonBuilder

case class Clients[F[_]](healthService: HealthService[F]):

end Clients

object Clients:

    private def buildClient[F[_]: Concurrent, Alg[_[_, _, _, _, _]]](
        service: smithy4s.Service[Alg]
    )(using http4sClient: Client[F]): Resource[F, service.Impl[F]] =
        SimpleRestJsonBuilder(service)
            .client(http4sClient)
            .uri(Uri.unsafeFromString("http://localhost"))
            .resource

    def apply[F[_]: Concurrent](
        http4sClient: Client[F]
    ): Resource[F, Clients[F]] =
        given Client[F] = http4sClient
        for health <- buildClient(HealthService)
        yield new Clients(health)

end Clients
