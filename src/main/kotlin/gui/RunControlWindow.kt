package gui

import cpu.Cpu
import glm_.vec2.Vec2
import imgui.Cond
import imgui.ImGui

fun paintRunControlWindow(cpu: Cpu) {
  with(ImGui) {

    setNextWindowSize(Vec2(120, 200), Cond.FirstUseEver)
    setNextWindowPos(Vec2(220, 80), Cond.FirstUseEver)

    begin("Run control")

    text("Current op:")
    separator()
    if(cpu.currentOp != null) {
      text(cpu.currentOp!!.name)
    }
    else {
      text("None")
    }

    newLine()

    text("Next op:")
    separator()
    if(cpu.nextOp != null) {
      text(cpu.nextOp!!.name)
    }
    else {
      text("None")
    }

    newLine()

    text("Control:")
    separator()


    if(button("Step")) {
      cpu.executeNextInstruction()
    }

    end()
  }
}