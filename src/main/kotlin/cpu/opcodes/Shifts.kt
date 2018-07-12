package cpu.opcodes

import cpu.Operation
import cpu.Registers
import cpu.Registers.Flag
import BitManipulation as bm

val shiftsExtended = mapOf(

  0x27 to Operation("SLA A", 0, 8, {r, _, _ -> r.A = shiftLeft(r.A, r) }),
  0x20 to Operation("SLA B", 0, 8, {r, _, _ -> r.A = shiftLeft(r.B, r) }),
  0x21 to Operation("SLA C", 0, 8, {r, _, _ -> r.A = shiftLeft(r.C, r) }),
  0x22 to Operation("SLA D", 0, 8, {r, _, _ -> r.A = shiftLeft(r.D, r) }),
  0x23 to Operation("SLA E", 0, 8, {r, _, _ -> r.A = shiftLeft(r.E, r) }),
  0x24 to Operation("SLA H", 0, 8, {r, _, _ -> r.A = shiftLeft(r.H, r) }),
  0x25 to Operation("SLA L", 0, 8, {r, _, _ -> r.A = shiftLeft(r.L, r) }),
  0x26 to Operation("SLA (HL)", 0, 16, {r, m, _ ->  m.writeByte(r.HL, shiftLeft(m.readByte(r.HL), r))}),

  0x2F to Operation("SRA A", 0, 8, {r, _, _ -> r.A = shiftRight(r.A, r) }),
  0x28 to Operation("SRA B", 0, 8, {r, _, _ -> r.A = shiftRight(r.B, r) }),
  0x29 to Operation("SRA C", 0, 8, {r, _, _ -> r.A = shiftRight(r.C, r) }),
  0x2A to Operation("SRA D", 0, 8, {r, _, _ -> r.A = shiftRight(r.D, r) }),
  0x2B to Operation("SRA E", 0, 8, {r, _, _ -> r.A = shiftRight(r.E, r) }),
  0x2C to Operation("SRA H", 0, 8, {r, _, _ -> r.A = shiftRight(r.H, r) }),
  0x2D to Operation("SRA L", 0, 8, {r, _, _ -> r.A = shiftRight(r.L, r) }),
  0x2E to Operation("SRA (HL)", 0, 16, {r, m, _ ->  m.writeByte(r.HL, shiftRight(m.readByte(r.HL), r))}),

  0x3F to Operation("SRL A", 0, 8, {r, _, _ -> r.A = shiftRightMsb0(r.A, r) }),
  0x38 to Operation("SRL B", 0, 8, {r, _, _ -> r.A = shiftRightMsb0(r.B, r) }),
  0x39 to Operation("SRL C", 0, 8, {r, _, _ -> r.A = shiftRightMsb0(r.C, r) }),
  0x3A to Operation("SRL D", 0, 8, {r, _, _ -> r.A = shiftRightMsb0(r.D, r) }),
  0x3B to Operation("SRL E", 0, 8, {r, _, _ -> r.A = shiftRightMsb0(r.E, r) }),
  0x3C to Operation("SRL H", 0, 8, {r, _, _ -> r.A = shiftRightMsb0(r.H, r) }),
  0x3D to Operation("SRL L", 0, 8, {r, _, _ -> r.A = shiftRightMsb0(r.L, r) }),
  0x3E to Operation("SRL (HL)", 0, 16, {r, m, _ ->  m.writeByte(r.HL, shiftRightMsb0(m.readByte(r.HL), r))})

)

private fun shiftLeft(n: Int, r: Registers): Int {

  val result = (n shl 1) and 0xFF

  r.setFlagFromBool(Flag.ZERO, result == 0)
  r.clearFlag(Flag.SUBTRACT)
  r.clearFlag(Flag.HALF_CARRY)
  r.setFlagFromBool(Flag.CARRY, (n and (1 shl 7)) != 0)

  return result
}

private fun shiftRight(n: Int, r: Registers): Int {

  val result = (n shr 1) or (n and (1 shl 7))

  r.setFlagFromBool(Flag.ZERO, result == 0)
  r.clearFlag(Flag.SUBTRACT)
  r.clearFlag(Flag.HALF_CARRY)
  r.setFlagFromBool(Flag.CARRY, (n and 1) != 0)

  return result
}

private fun shiftRightMsb0(n: Int, r: Registers): Int {

  val result = (n shr 1)

  r.setFlagFromBool(Flag.ZERO, result == 0)
  r.clearFlag(Flag.SUBTRACT)
  r.clearFlag(Flag.HALF_CARRY)
  r.setFlagFromBool(Flag.CARRY, (n and 1) != 0)

  return result
}