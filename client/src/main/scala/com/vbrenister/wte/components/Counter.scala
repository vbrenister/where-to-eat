package com.vbrenister.wte.components

import com.raquo.laminar.api.L.*
import com.raquo.laminar.api.L.given

object Counter {

  def apply(initial: Int, label: String): (HtmlElement, Signal[Int]) = {
    val count = Var(initial = initial)

    val component =
      button(label, tpe("button"), onClick --> { _ => count.update(_ + 1) })

    (component, count.signal)
  }

}
