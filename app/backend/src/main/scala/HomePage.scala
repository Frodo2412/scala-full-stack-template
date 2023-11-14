package com.example.app.api

import tyrian.*
import tyrian.Html.*

object HomePage:

  val page: Html[Nothing] =
    html(
      head(
        meta(charset := "UTF-8"),
        title("Tyrian SPA")
      ),
      body(
        script(_type := "text/javascript")(
          "var process = { env: { 'CATS_EFFECT_TRACING_MODE': 'NONE' } };"
        ),
        div(id := "myapp")(),
        script(typ := "module", src := "./opt.js")()
      )
    )

end HomePage
