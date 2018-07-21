package gui

import glm_.vec2.Vec2
import imgui.Cond
import imgui.ImGui

fun paintEmulationOutputWindow() {
  with(ImGui) {
    setNextWindowSize(Vec2(640, 576), Cond.FirstUseEver)
    setNextWindowPos(Vec2(400, 50), Cond.FirstUseEver)
    begin("Emulation output - 4x scale")
    text("TODO - actually render something...")
    end()
  }
}