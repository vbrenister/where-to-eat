package com.vbrenister.wte.pages

import com.raquo.laminar.api.L.{*, given}
import com.vbrenister.wte.components.Counter

object Home {
  def apply(): HtmlElement = {
    val (counter, count) = Counter(0, "Give me food!")

    div(
      h1("Where to Eat?"),
      p("Click if you are hungry"),
      span("Hungry index: ", child.text <-- count),
      br(),
      counter
    )
  }
}
