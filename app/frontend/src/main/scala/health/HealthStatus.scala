package com.example.app.ui
package health

sealed trait HealthStatus

object HealthStatus:

    case object Pending   extends HealthStatus
    case object Healthy   extends HealthStatus
    case object Unhealthy extends HealthStatus

end HealthStatus
