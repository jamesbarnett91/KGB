package gui

import cpu.Registers
import glm_.vec2.Vec2
import imgui.Cond
import imgui.ImGui

fun paintCpuRegisterWindow(registers: Registers) {
  with(ImGui) {
    setNextWindowSize(Vec2(200, 250), Cond.FirstUseEver)
    setNextWindowPos(Vec2(15, 80), Cond.FirstUseEver)
    begin("CPU state")

    text("Registers:")
    separator()

    columns(2, "cpuReg", false)

    text(String.format("A: 0x%02X", registers.A))
    nextColumn()
    text(String.format("F: 0x%02X", registers.F))
    nextColumn()
    text(String.format("B: 0x%02X", registers.B))
    nextColumn()
    text(String.format("C: 0x%02X", registers.C))
    nextColumn()
    text(String.format("D: 0x%02X", registers.D))
    nextColumn()
    text(String.format("E: 0x%02X", registers.E))
    nextColumn()
    text(String.format("H: 0x%02X", registers.H))
    nextColumn()
    text(String.format("L: 0x%02X", registers.L))
    nextColumn()
    text(String.format("SP: 0x%04X", registers.SP))
    nextColumn()
    text(String.format("PC: 0x%04X", registers.PC))

    columns(1)
    newLine()
    text("Flags:")
    separator()

    text("ZERO: ${registers.getFlag(Registers.Flag.ZERO) == 1}")
    text("SUBTRACT: ${registers.getFlag(Registers.Flag.SUBTRACT) == 1}")
    text("HALF_CARRY: ${registers.getFlag(Registers.Flag.HALF_CARRY) == 1}")
    text("CARRY: ${registers.getFlag(Registers.Flag.CARRY) == 1}")

    end()
  }
}

