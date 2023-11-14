package com.example.app.ui
package health

import tyrian.Html
import tyrian.Html.div

object HealthPage:

    def render(status: HealthStatus): Html[Nothing] = div {
        status match
            case HealthStatus.Pending   => "Waiting for service"
            case HealthStatus.Healthy   => "Service healthy"
            case HealthStatus.Unhealthy => "Service unhealthy"
    }

end HealthPage
