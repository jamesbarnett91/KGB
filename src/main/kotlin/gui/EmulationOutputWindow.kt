package gui

import glm_.vec2.Vec2
import imgui.*

fun paintEmulationOutputWindow(frame: TextureID) {
  with(ImGui) {

    pushStyleVar(StyleVar.WindowPadding, Vec2(0))

    setNextWindowSize(Vec2(640, 596), Cond.FirstUseEver)
    setNextWindowPos(Vec2(620, 10), Cond.FirstUseEver)

    begin("Emulation output - 4x scale", flags_ =  WindowFlag.NoScrollbar or WindowFlag.NoResize)

      image(frame, Vec2(640,576))

    end()

    popStyleVar()
  }
}