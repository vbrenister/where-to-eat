package com.vbrenister.wte

import com.raquo.laminar.api.L.*
import com.raquo.laminar.api.L.given
import com.vbrenister.wte.pages.Home
import org.scalajs.dom

@main
def App(): Unit =
  renderOnDomContentLoaded(
    dom.document.getElementById("app"),
    Home()
  )
