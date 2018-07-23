package cpu.opcodes

import cpu.Operation
import cpu.Registers
import cpu.Registers.Flag
import BitManipulation as bm

val rotates = mapOf(

  0x07 to Operation("RLCA", 0, 4) { r, _, _ -> r.A = rotateLeft(r.A, r) },

  0x17 to Operation("RLA", 0, 4) { r, _, _ -> r.A = rotateLeftThroughCarry(r.A, r) },

  0x0F to Operation("RRCA", 0, 4) { r, _, _ -> r.A = rotateRight(r.A, r) },

  0x1F to Operation("RRA", 0, 4) { r, _, _ -> r.A = rotateRightThroughCarry(r.A, r) }

)

val rotatesExtended = mapOf(

  0x07 to Operation("RLC A", 0, 8) { r, _, _ -> r.A = rotateLeft(r.A, r) },
  0x00 to Operation("RLC B", 0, 8) { r, _, _ -> r.A = rotateLeft(r.B, r) },
  0x01 to Operation("RLC C", 0, 8) { r, _, _ -> r.A = rotateLeft(r.C, r) },
  0x02 to Operation("RLC D", 0, 8) { r, _, _ -> r.A = rotateLeft(r.D, r) },
  0x03 to Operation("RLC E", 0, 8) { r, _, _ -> r.A = rotateLeft(r.E, r) },
  0x04 to Operation("RLC H", 0, 8) { r, _, _ -> r.A = rotateLeft(r.H, r) },
  0x05 to Operation("RLC L", 0, 8) { r, _, _ -> r.A = rotateLeft(r.L, r) },
  0x06 to Operation("RLC (HL)", 0, 16) { r, m, _ ->  m.writeByte(r.HL, rotateLeft(m.readByte(r.HL), r))},

  0x17 to Operation("RL A", 0, 8) { r, _, _ -> r.A = rotateLeftThroughCarry(r.A, r) },
  0x10 to Operation("RL B", 0, 8) { r, _, _ -> r.A = rotateLeftThroughCarry(r.B, r) },
  0x11 to Operation("RL C", 0, 8) { r, _, _ -> r.A = rotateLeftThroughCarry(r.C, r) },
  0x12 to Operation("RL D", 0, 8) { r, _, _ -> r.A = rotateLeftThroughCarry(r.D, r) },
  0x13 to Operation("RL E", 0, 8) { r, _, _ -> r.A = rotateLeftThroughCarry(r.E, r) },
  0x14 to Operation("RL H", 0, 8) { r, _, _ -> r.A = rotateLeftThroughCarry(r.H, r) },
  0x15 to Operation("RL L", 0, 8) { r, _, _ -> r.A = rotateLeftThroughCarry(r.L, r) },
  0x16 to Operation("RL (HL)", 0, 16) { r, m, _ ->  m.writeByte(r.HL, rotateLeftThroughCarry(m.readByte(r.HL), r))},

  0x0F to Operation("RRC A", 0, 8) { r, _, _ -> r.A = rotateRight(r.A, r) },
  0x08 to Operation("RRC B", 0, 8) { r, _, _ -> r.A = rotateRight(r.B, r) },
  0x09 to Operation("RRC C", 0, 8) { r, _, _ -> r.A = rotateRight(r.C, r) },
  0x0A to Operation("RRC D", 0, 8) { r, _, _ -> r.A = rotateRight(r.D, r) },
  0x0B to Operation("RRC E", 0, 8) { r, _, _ -> r.A = rotateRight(r.E, r) },
  0x0C to Operation("RRC H", 0, 8) { r, _, _ -> r.A = rotateRight(r.H, r) },
  0x0D to Operation("RRC L", 0, 8) { r, _, _ -> r.A = rotateRight(r.L, r) },
  0x0E to Operation("RRC (HL)", 0, 16) { r, m, _ ->  m.writeByte(r.HL, rotateRight(m.readByte(r.HL), r))},

  0x1F to Operation("RR A", 0, 8) { r, _, _ -> r.A = rotateRightThroughCarry(r.A, r) },
  0x18 to Operation("RR B", 0, 8) { r, _, _ -> r.A = rotateRightThroughCarry(r.B, r) },
  0x19 to Operation("RR C", 0, 8) { r, _, _ -> r.A = rotateRightThroughCarry(r.C, r) },
  0x1A to Operation("RR D", 0, 8) { r, _, _ -> r.A = rotateRightThroughCarry(r.D, r) },
  0x1B to Operation("RR E", 0, 8) { r, _, _ -> r.A = rotateRightThroughCarry(r.E, r) },
  0x1C to Operation("RR H", 0, 8) { r, _, _ -> r.A = rotateRightThroughCarry(r.H, r) },
  0x1D to Operation("RR L", 0, 8) { r, _, _ -> r.A = rotateRightThroughCarry(r.L, r) },
  0x1E to Operation("RR (HL)", 0, 16) { r, m, _ ->  m.writeByte(r.HL, rotateRightThroughCarry(m.readByte(r.HL), r))}

)

private fun rotateLeft(n: Int, r: Registers): Int {

  var result = (n shl 1) and 0xFF

  r.clearFlag(Flag.SUBTRACT)
  r.clearFlag(Flag.HALF_CARRY)
  if (n and (1 shl 7) != 0) {
    result = result or 1
    r.setFlag(Flag.CARRY)
  } else {
    r.clearFlag(Flag.CARRY)
  }

  r.setFlagFromBool(Flag.ZERO, result == 0)

  return result
}

private fun rotateLeftThroughCarry(n: Int, r: Registers): Int {

  var result = (n shl 1) and 0xFF
  result = result or r.getFlag(Flag.CARRY)

  r.setFlagFromBool(Flag.ZERO, result == 0)
  r.clearFlag(Flag.SUBTRACT)
  r.clearFlag(Flag.HALF_CARRY)
  r.setFlagFromBool(Flag.CARRY, (n and (1 shl 7)) != 0)

  return result
}

private fun rotateRight(n: Int, r: Registers): Int {

  var result = n shr 1

  r.clearFlag(Flag.SUBTRACT)
  r.clearFlag(Flag.HALF_CARRY)

  if ((n and 1) == 1) {
    result = result or (1 shl 7)
    r.setFlag(Flag.CARRY)
  } else {
    r.clearFlag(Flag.CARRY)
  }

  r.setFlagFromBool(Flag.ZERO, result == 0)

  return result
}

private fun rotateRightThroughCarry(n: Int, r: Registers): Int {

  var result = n shr 1
  var carry = 0
  if (r.getFlag(Flag.CARRY) == 1){
    carry = 1 shl 7
  }

  result = result or carry

  r.setFlagFromBool(Flag.ZERO, result == 0)
  r.clearFlag(Flag.SUBTRACT)
  r.clearFlag(Flag.HALF_CARRY)
  r.setFlagFromBool(Flag.CARRY, (n and 1) != 0)

  return result
}