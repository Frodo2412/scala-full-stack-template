package com.example.app.ui

import health.HealthStatus

sealed trait Model

object Model:

    case class Health(health: HealthStatus) extends Model
    case class Error(error: String)         extends Model

end Model
