package cpu.opcodes

import cpu.Operation
import cpu.Registers
import cpu.Registers.Flag
import BitManipulation as bm

var arithmetic16Bit = mapOf(

  0x09 to Operation("ADD HL,BC", 0, 8) { r, _, _ -> r.HL = add(r.HL, r.BC, r)},
  0x19 to Operation("ADD HL,DE", 0, 8) { r, _, _ -> r.HL = add(r.HL, r.DE, r)},
  0x29 to Operation("ADD HL,HL", 0, 8) { r, _, _ -> r.HL = add(r.HL, r.HL, r)},
  0x39 to Operation("ADD HL,SP", 0, 8) { r, _, _ -> r.HL = add(r.HL, r.SP, r)},

  0xE8 to Operation("ADD SP,n", 1, 16) { r, _, a ->
    r.clearFlags()

    val absoluteValue = bm.getAbsoluteValue(a[0])

    if (bm.isSignedByteNegative(a[0])) {
      r.setFlagFromBool(Flag.HALF_CARRY, r.SP and 0x0F < absoluteValue and 0x0F)
      r.setFlagFromBool(Flag.CARRY,r.SP and 0xFF < absoluteValue)
      r.SP = r.SP - absoluteValue

    } else {
      r.setFlagFromBool(Flag.HALF_CARRY,(r.SP and 0x0F) + (absoluteValue and 0x0F) > 0x0F)
      r.setFlagFromBool(Flag.CARRY,(r.SP and 0xFF) + absoluteValue > 0xFF)
      r.SP = r.SP + absoluteValue
    }
  },

  0x03 to Operation("INC BC", 0, 8) { r, _, _ -> r.BC = inc(r.BC)},
  0x13 to Operation("INC DE", 0, 8) { r, _, _ -> r.DE = inc(r.DE)},
  0x23 to Operation("INC HL", 0, 8) { r, _, _ -> r.HL = inc(r.HL)},
  0x33 to Operation("INC SP", 0, 8) { r, _, _ -> r.SP = inc(r.SP)},

  0x0B to Operation("DEC BC", 0, 8) { r, _, _ -> r.BC = dec(r.BC)},
  0x1B to Operation("DEC DE", 0, 8) { r, _, _ -> r.DE = dec(r.DE)},
  0x2B to Operation("DEC HL", 0, 8) { r, _, _ -> r.HL = dec(r.HL)},
  0x3B to Operation("DEC SP", 0, 8) { r, _, _ -> r.SP = dec(r.SP)}

)

private fun add(n1: Int, n2: Int, r: Registers): Int {

  r.clearFlag(Flag.SUBTRACT)
  r.setFlagFromBool(Flag.HALF_CARRY, ((n1 and 0x0FFF) + (n2 and 0x0FFF)) > 0x0FFF)
  r.setFlagFromBool(Flag.CARRY, (n1 + n2) > 0xFFFF)

  return (n1 + n2) and 0xFFFF
}


private fun inc(n: Int): Int {
  return (n + 1) and 0xFFFF
}

private fun dec(n: Int): Int {
  return (n - 1) and 0xFFFF
}