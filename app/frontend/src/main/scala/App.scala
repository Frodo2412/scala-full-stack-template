package com.example.app.ui

import health.{HealthPage, HealthService, HealthStatus}

import cats.effect.IO
import tyrian.*
import tyrian.Html.*

import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("TyrianApp")
object App extends TyrianApp[Msg, Model]:

    override def router: Location => Msg = Routing.none(Msg.NoOp)

    override def init(flags: Map[String, String]): (Model, Cmd[IO, Msg]) =
        (Model.Health(HealthStatus.Pending), HealthService.checkStatus)

    override def update(model: Model): Msg => (Model, Cmd[IO, Msg]) = msg =>
        (
          msg match
              case Msg.NoOp                => model
              case Msg.GetStatus(status)   =>
                  Model.Health(
                    if status == 200 then HealthStatus.Healthy
                    else HealthStatus.Unhealthy
                  )
              case Msg.NetworkError(error) => Model.Error(error)
          ,
          Cmd.None
        )

    override def view(model: Model): Html[Msg] = model match
        case Model.Health(health) => HealthPage.render(health)
        case Model.Error(error)   => div(s"Unable to contact backend $error")

    override def subscriptions(model: Model): Sub[IO, Msg] = Sub.None

end App
