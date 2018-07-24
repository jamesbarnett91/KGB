package gui

import glm_.vec2.Vec2
import imgui.Cond
import imgui.ImGui
import ram.Ram

fun paintRamDumpWindow(ram: Ram) {
  with(ImGui) {
    setNextWindowSize(Vec2(425, 300), Cond.FirstUseEver)
    setNextWindowPos(Vec2(15, 340), Cond.FirstUseEver)

    begin("Ram dump")

    // TODO - lock range to scroll position
    paintRamRange(0, 0x00FF, ram.ram)

    end()
  }
}

private fun paintRamRange(startAddress: Int, endAddress: Int, data: IntArray) {
  (startAddress .. endAddress step 16).forEach {
    paintLine(it, data)
  }
}

private fun paintLine(startAddress: Int, data: IntArray) {
  val sb = StringBuilder()
  sb.append(String.format("0x%04X: ", startAddress))

  (0..15).forEach {
    val address = startAddress + it
    if(address <= (data.size-1)) {
      sb.append(String.format("%02X ", data[startAddress + it]))
      if(it == 7) {sb.append(" ")}
    }
  }

  ImGui.text(sb.toString())
}


