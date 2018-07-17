package cpu.opcodes

import cpu.Operation
import cpu.Registers
import ram.Ram
import BitManipulation as bm

val returns = mapOf(

  0xC9 to Operation("RET", 0, 8, {r, m, _ -> ret(r, m)}),

  0xC0 to Operation("RET NZ,nn", 0, 8, {r, m, _ -> if(r.getFlag(Registers.Flag.ZERO) == 0) ret(r, m)}),
  0xC8 to Operation("RET Z,nn", 0, 8, {r, m, _ -> if(r.getFlag(Registers.Flag.ZERO) == 1) ret(r, m)}),
  0xD0 to Operation("RET NC,nn", 0, 8, {r, m, _ -> if(r.getFlag(Registers.Flag.CARRY) == 0) ret(r, m)}),
  0xD8 to Operation("RET C,nn", 0, 8, {r, m, _ -> if(r.getFlag(Registers.Flag.CARRY) == 1) ret(r, m)}),

  // TODO - enable interrupts once added
  0xC9 to Operation("RET", 0, 8, {r, m, _ -> ret(r, m)})

)

private fun ret(r: Registers, m: Ram) {

  val lsb = m.readByte(r.getAndIncrementSP())
  val msb = m.readByte(r.getAndIncrementSP())

  r.PC = bm.bytesToWord(msb, lsb)
}