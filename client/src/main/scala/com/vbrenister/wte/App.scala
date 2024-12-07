package com.vbrenister.wte

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
import com.vbrenister.wte.pages.Home

@main
def App(): Unit =
  renderOnDomContentLoaded(
    dom.document.getElementById("app"),
    Home()
  )
