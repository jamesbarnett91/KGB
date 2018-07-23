package cpu.opcodes

import cpu.Operation
import cpu.Registers.Flag
import BitManipulation as bm

val jumps = mapOf(

  0xC3 to Operation("JP nn", 2, 12) { r, _, a -> r.PC = bm.argsToWord(a)},
  0xC2 to Operation("JP NZ,nn", 2, 12) { r, _, a -> if(r.getFlag(Flag.ZERO) == 0) r.PC = bm.argsToWord(a)},
  0xCA to Operation("JP Z,nn", 2, 12) { r, _, a -> if(r.getFlag(Flag.ZERO) == 1) r.PC = bm.argsToWord(a)},
  0xD2 to Operation("JP NC,nn", 2, 12) { r, _, a -> if(r.getFlag(Flag.CARRY) == 0) r.PC = bm.argsToWord(a)},
  0xDA to Operation("JP C,nn", 2, 12) { r, _, a -> if(r.getFlag(Flag.CARRY) == 1) r.PC = bm.argsToWord(a)},
  0xE9 to Operation("JP (HL)", 0, 4) { r, m, _ -> r.PC = m.readByte(r.HL)},

  0x18 to Operation("JR n", 1, 8) { r, _, a -> r.addSignedByteToPC(a[0])},
  0x20 to Operation("JR NZ,n", 1, 8) { r, _, a -> if(r.getFlag(Flag.ZERO) == 0) r.addSignedByteToPC(a[0])},
  0x28 to Operation("JR Z,n", 1, 8) { r, _, a -> if(r.getFlag(Flag.ZERO) == 1) r.addSignedByteToPC(a[0])},
  0x30 to Operation("JR NC,n", 1, 8) { r, _, a -> if(r.getFlag(Flag.CARRY) == 0) r.addSignedByteToPC(a[0])},
  0x38 to Operation("JR C,n", 1, 8) { r, _, a -> if(r.getFlag(Flag.CARRY) == 1) r.addSignedByteToPC(a[0])}

)