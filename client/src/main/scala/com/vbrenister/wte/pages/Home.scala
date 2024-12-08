package com.vbrenister.wte.pages

import com.raquo.laminar.api.L.*
import com.raquo.laminar.api.L.given
import com.vbrenister.wte.components.Counter

object Home {

  def apply(): HtmlElement = {
    val responseStream   = Var(List.empty[String])
    val (counter, count) = Counter(0, "Give me food!")

    div(
      h1("Where to Eat?"),
      p("Click if you are hungry"),
      span("Hungry index: ", child.text <-- count),
      br(),
      counter,
      br(),
      button(
        "Fetch data",
        onClick.flatMap(_ =>
          FetchStream.get("http://localhost:8080/ping")
        ) --> { responseText =>
          responseStream
            .update(curr => curr :+ responseText)
        }
      ),
      p("Responses:", child.text <-- responseStream.signal.map(_.mkString))
    )
  }

}
