package gui

import GameBoy
import cpu.Cpu
import gli_.Texture
import gli_.gli
import glm_.vec2.Vec2i
import glm_.vec4.Vec4
import gln.checkError
import gln.texture.glDeleteTexture
import gln.texture.initTexture2d
import gpu.TestPatternGpu
import imgui.Context
import imgui.ImGui
import imgui.TextureID
import imgui.destroy
import imgui.impl.LwjglGL3
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30
import uno.glfw.GlfwWindow
import uno.glfw.glfw
import java.awt.image.BufferedImage
import java.io.File

class WindowContainer {

  val gameBoy: GameBoy

  init {
    glfw.init("3.2")

    gameBoy = GameBoy(Cpu(), TestPatternGpu())
    gameBoy.loadRom(File("src/main/resources/roms/boot-rom.gb"))
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

    val (texture, textureId) = createAndRegisterTexture(gameBoy.gpu.getFrame())

    with(ImGui) {
      paintDebugWindow()
      paintCpuRegisterWindow(gameBoy.cpu.registers)
      paintRunControlWindow(gameBoy.cpu)
      paintRamDumpWindow(gameBoy.cpu.ram)
      paintEmulationOutputWindow(textureId)
    }

    gln.glViewport(window.framebufferSize)
    gln.glClearColor(Vec4(0.45f, 0.55f, 0.6f, 1f))
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)

    ImGui.render()
    LwjglGL3.renderDrawData(ImGui.drawData!!)

    // TODO - render direct to framebuffer rather than creating and destroying texture each frame
    disposeTexture(texture, textureId)

    checkError("mainLoop")
  }

  private fun createAndRegisterTexture(frame: BufferedImage): Pair<Texture, TextureID> {
    val texture = gli.createTexture(frame)
    val textureId = initTexture2d {
      minFilter = linear
      magFilter = linear
      GL30.glPixelStorei(GL30.GL_UNPACK_ROW_LENGTH, 0)
      image(GL30.GL_RGB, Vec2i(640,576), GL30.GL_RGB, GL30.GL_UNSIGNED_BYTE, texture.data())
    }

    return Pair(texture, textureId)
  }

  private fun disposeTexture(texture: Texture, textureID: TextureID) {
    glDeleteTexture(textureID)
    texture.dispose()
  }

}