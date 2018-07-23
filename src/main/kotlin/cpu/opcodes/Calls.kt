package cpu.opcodes

import cpu.Operation
import cpu.Registers
import cpu.Registers.Flag
import ram.Ram
import BitManipulation as bm

val calls = mapOf(

  0xCD to Operation("CALL nn", 2, 12) { r, m, a -> call(bm.argsToWord(a), r, m)},

  0xC4 to Operation("CALL NZ,nn", 2, 12) { r, m, a -> if(r.getFlag(Flag.ZERO) == 0) call(bm.argsToWord(a), r, m)},
  0xCC to Operation("CALL Z,nn", 2, 12) { r, m, a -> if(r.getFlag(Flag.ZERO) == 1) call(bm.argsToWord(a), r, m)},
  0xD4 to Operation("CALL NC,nn", 2, 12) { r, m, a -> if(r.getFlag(Flag.CARRY) == 0) call(bm.argsToWord(a), r, m)},
  0xDC to Operation("CALL C,nn", 2, 12) { r, m, a -> if(r.getFlag(Flag.CARRY) == 1) call(bm.argsToWord(a), r, m)}

)

private fun call(word: Int, r: Registers, m: Ram) {

  val nextAddress = (r.PC + 3) and 0xFFFF

  m.writeByte(r.decrementAndGetSP(), bm.getMsb(nextAddress))
  m.writeByte(r.decrementAndGetSP(), bm.getLsb(nextAddress))

  r.PC = word
}