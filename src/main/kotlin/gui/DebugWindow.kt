package gui

import glm_.vec2.Vec2
import imgui.Cond
import imgui.ImGui

fun paintDebugWindow() {
  with(ImGui) {
    setNextWindowPos(Vec2(15, 10), Cond.FirstUseEver)
    begin("Debug info")
    text("%.1f FPS (%.2f ms/frame)", io.framerate, 1_000f / io.framerate)
    end()
  }
}