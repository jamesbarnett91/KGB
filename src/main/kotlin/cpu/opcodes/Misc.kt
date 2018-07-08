package cpu.opcodes

import cpu.Operation
import cpu.Registers
import cpu.Registers.Flag
import BitManipulation as bm

var misc = mapOf(

  0x37 to Operation("SWAP A", 0, 8, {r, _, _ -> r.A = swap(r.A, r)}),
  0x30 to Operation("SWAP B", 0, 8, {r, _, _ -> r.B = swap(r.B, r)}),
  0x31 to Operation("SWAP C", 0, 8, {r, _, _ -> r.C = swap(r.C, r)}),
  0x32 to Operation("SWAP D", 0, 8, {r, _, _ -> r.D = swap(r.D, r)}),
  0x33 to Operation("SWAP E", 0, 8, {r, _, _ -> r.E = swap(r.E, r)}),
  0x34 to Operation("SWAP H", 0, 8, {r, _, _ -> r.H = swap(r.H, r)}),
  0x35 to Operation("SWAP L", 0, 8, {r, _, _ -> r.L = swap(r.L, r)}),
  0x36 to Operation("SWAP (HL)", 0, 16, {r, m, _ -> m.writeByte(r.HL, swap(m.readByte(r.HL), r))}),

  0x27 to Operation("DAA", 0, 4, {r, _, _ ->
    // TODO
  }),

  0x2F to Operation("CPL", 0, 4, {r, _, _ ->
    r.A = r.A.inv() and 0xFF
    r.setFlag(Flag.SUBTRACT)
    r.setFlag(Flag.HALF_CARRY)
  }),

  0x3F to Operation("CCF", 0, 4, {r, _, _ ->
    r.setFlagFromBool(Flag.CARRY, r.getFlag(Flag.CARRY) == 0)
    r.clearFlag(Flag.SUBTRACT)
    r.clearFlag(Flag.HALF_CARRY)
  }),

  0x37 to Operation("SCF", 0, 4, {r, _, _ ->
    r.clearFlag(Flag.SUBTRACT)
    r.clearFlag(Flag.HALF_CARRY)
    r.setFlag(Flag.CARRY)
  }),

  0x00 to Operation("NOP", 0, 4, {_, _, _ -> }),

  // TODO - interrupt related
  0x76 to Operation("HALT", 0, 4, {_, _, _ -> }),
  0x10 to Operation("STOP", 0, 4, {_, _, _ -> }),
  0xD3 to Operation("DI", 0, 4, {_, _, _ -> }),
  0xFB to Operation("EI", 0, 4, {_, _, _ -> })

)


private fun swap(n: Int, r: Registers): Int {

  val topNibble = n and 0xF0
  val bottomNibble = n and 0x0F
  val result = (bottomNibble shl 4) or (topNibble shr 4)
  r.clearFlags()
  r.setFlagFromBool(Flag.ZERO, result == 0)

  return result
}
