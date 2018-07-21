package gui

import cpu.Cpu
import glm_.vec4.Vec4
import gln.checkError
import imgui.Context
import imgui.ImGui
import imgui.destroy
import imgui.impl.LwjglGL3
import org.lwjgl.opengl.GL11
import uno.glfw.GlfwWindow
import uno.glfw.glfw

class WindowContainer {

  var cpu: Cpu

  init {
    glfw.init("3.2")
    cpu = Cpu()
  }

  val window = GlfwWindow(1280, 720, "KGB - KotlinGameBoy").apply {
    init()
  }


  fun run() {

    // Enable vsync
    glfw.swapInterval = 1

    val ctx = Context()
    LwjglGL3.init(window)
    ImGui.styleColorsDark()

    window.loop(::mainLoop)

    LwjglGL3.shutdown()
    ctx.destroy()

    window.destroy()
    glfw.terminate()

  }

  private fun mainLoop() {

    LwjglGL3.newFrame()

    with(ImGui) {
      paintDebugWindow()
      paintEmulationOutputWindow()
      paintCpuRegisterWindow(cpu.registers)
    }

    gln.glViewport(window.framebufferSize)
    gln.glClearColor(Vec4(0.45f, 0.55f, 0.6f, 1f))
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)

    ImGui.render()
    LwjglGL3.renderDrawData(ImGui.drawData!!)

    checkError("mainLoop")
  }
}