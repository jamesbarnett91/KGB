package cpu.opcodes

import cpu.Operation
import cpu.Registers
import ram.Ram
import BitManipulation as bm

val restarts = mapOf(

  0xC7 to Operation("RST 00H", 0, 32, {r, m, _ -> restart(0x00, r, m)}),
  0xCF to Operation("RST 08H", 0, 32, {r, m, _ -> restart(0x08, r, m)}),
  0xD7 to Operation("RST 10H", 0, 32, {r, m, _ -> restart(0x10, r, m)}),
  0xDF to Operation("RST 18H", 0, 32, {r, m, _ -> restart(0x18, r, m)}),
  0xE7 to Operation("RST 20H", 0, 32, {r, m, _ -> restart(0x20, r, m)}),
  0xEF to Operation("RST 28H", 0, 32, {r, m, _ -> restart(0x28, r, m)}),
  0xF7 to Operation("RST 30H", 0, 32, {r, m, _ -> restart(0x30, r, m)}),
  0xFF to Operation("RST 38H", 0, 32, {r, m, _ -> restart(0x38, r, m)})

)

private fun restart(word: Int, r: Registers, m: Ram) {

  m.writeByte(r.decrementAndGetSP(), bm.getMsb(r.PC))
  m.writeByte(r.decrementAndGetSP(), bm.getLsb(r.PC))

  r.PC = word
}