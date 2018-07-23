package cpu.opcodes

import cpu.Operation
import cpu.Registers
import cpu.Registers.Flag
import BitManipulation as bm

fun generateExtendedBitOps(): Map<Int, Operation> {
  val bitOps = mutableMapOf<Int, Operation>()

  IntRange(0, 7).forEach{ i ->
    bitOps[0x47 + (0x08 * i)] = Operation("BIT $i,A", 0, 8) { r, _, _ -> bit(r.A, i, r) }
    bitOps[0x40 + (0x08 * i)] = Operation("BIT $i,B", 0, 8) { r, _, _ -> bit(r.B, i, r) }
    bitOps[0x41 + (0x08 * i)] = Operation("BIT $i,C", 0, 8) { r, _, _ -> bit(r.C, i, r) }
    bitOps[0x42 + (0x08 * i)] = Operation("BIT $i,D", 0, 8) { r, _, _ -> bit(r.D, i, r) }
    bitOps[0x43 + (0x08 * i)] = Operation("BIT $i,E", 0, 8) { r, _, _ -> bit(r.E, i, r) }
    bitOps[0x44 + (0x08 * i)] = Operation("BIT $i,H", 0, 8) { r, _, _ -> bit(r.H, i, r) }
    bitOps[0x45 + (0x08 * i)] = Operation("BIT $i,L", 0, 8) { r, _, _ -> bit(r.L, i, r) }
    bitOps[0x46 + (0x08 * i)] = Operation("BIT $i,(HL)", 0, 16) { r, m, _ -> bit(m.readByte(r.HL), i, r) }

    bitOps[0xC7 + (0x08 * i)] = Operation("SET $i,A", 0, 8) { r, _, _ -> r.A = set(r.A, i) }
    bitOps[0xC0 + (0x08 * i)] = Operation("SET $i,B", 0, 8) { r, _, _ -> r.B = set(r.B, i) }
    bitOps[0xC1 + (0x08 * i)] = Operation("SET $i,C", 0, 8) { r, _, _ -> r.C = set(r.C, i) }
    bitOps[0xC2 + (0x08 * i)] = Operation("SET $i,D", 0, 8) { r, _, _ -> r.D = set(r.D, i) }
    bitOps[0xC3 + (0x08 * i)] = Operation("SET $i,E", 0, 8) { r, _, _ -> r.E = set(r.E, i) }
    bitOps[0xC4 + (0x08 * i)] = Operation("SET $i,H", 0, 8) { r, _, _ -> r.H = set(r.H, i) }
    bitOps[0xC5 + (0x08 * i)] = Operation("SET $i,L", 0, 8) { r, _, _ -> r.L = set(r.L, i) }
    bitOps[0xC6 + (0x08 * i)] = Operation("SET $i,(HL)", 0, 16) { r, m, _ -> m.writeByte(r.HL, set(m.readByte(r.HL), i)) }

    bitOps[0x87 + (0x08 * i)] = Operation("RES $i,A", 0, 8) { r, _, _ -> r.A = reset(r.A, i) }
    bitOps[0x80 + (0x08 * i)] = Operation("RES $i,B", 0, 8) { r, _, _ -> r.B = reset(r.B, i) }
    bitOps[0x81 + (0x08 * i)] = Operation("RES $i,C", 0, 8) { r, _, _ -> r.C = reset(r.C, i) }
    bitOps[0x82 + (0x08 * i)] = Operation("RES $i,D", 0, 8) { r, _, _ -> r.D = reset(r.D, i) }
    bitOps[0x83 + (0x08 * i)] = Operation("RES $i,E", 0, 8) { r, _, _ -> r.E = reset(r.E, i) }
    bitOps[0x84 + (0x08 * i)] = Operation("RES $i,H", 0, 8) { r, _, _ -> r.H = reset(r.H, i) }
    bitOps[0x85 + (0x08 * i)] = Operation("RES $i,L", 0, 8) { r, _, _ -> r.L = reset(r.L, i) }
    bitOps[0x86 + (0x08 * i)] = Operation("RES $i,(HL)", 0, 16) { r, m, _ -> m.writeByte(r.HL, reset(m.readByte(r.HL), i)) }
  }

  return bitOps.toMap()
}

private fun bit(reg: Int, bit: Int, r: Registers) {

  if (bit < 8) {
    r.setFlagFromBool(Flag.ZERO, (reg and (1 shl bit)) != 0)
  }

  r.clearFlag(Flag.SUBTRACT)
  r.setFlag(Flag.HALF_CARRY)
}

private fun set(byte: Int, position: Int): Int {
  return byte or ((1 shl position) and 0xFF)
}

private fun reset(byte: Int, position: Int): Int {
  return (1 shl position).inv() and byte and 0xFF
}