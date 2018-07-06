package cpu.opcodes

import cpu.Operation
import cpu.Registers
import cpu.Registers.Flag
import BitManipulation as bm

var arithmetic8Bit = mapOf(

  0x87 to Operation("ADD A,A", 0, 4, {r, _, _ -> r.A = add(r.A, r.A, r)}),
  0x80 to Operation("ADD A,B", 0, 4, {r, _, _ -> r.A = add(r.A, r.B, r)}),
  0x81 to Operation("ADD A,C", 0, 4, {r, _, _ -> r.A = add(r.A, r.C, r)}),
  0x82 to Operation("ADD A,D", 0, 4, {r, _, _ -> r.A = add(r.A, r.D, r)}),
  0x83 to Operation("ADD A,E", 0, 4, {r, _, _ -> r.A = add(r.A, r.E, r)}),
  0x84 to Operation("ADD A,H", 0, 4, {r, _, _ -> r.A = add(r.A, r.H, r)}),
  0x85 to Operation("ADD A,L", 0, 4, {r, _, _ -> r.A = add(r.A, r.L, r)}),
  0x86 to Operation("ADD A,(HL)", 0, 8, {r, m, _ -> r.A = add(r.A, m.readByte(r.HL), r)}),
  0xC6 to Operation("ADD A,n", 1, 8, {r, _, a -> r.A = add(r.A, a[0], r)}),

  0x8F to Operation("ADC A,A", 0, 4, {r, _, _ -> r.A = addWithCarry(r.A, r.A, r) }),
  0x88 to Operation("ADC A,B", 0, 4, {r, _, _ -> r.A = addWithCarry(r.A, r.B, r)}),
  0x89 to Operation("ADC A,C", 0, 4, {r, _, _ -> r.A = addWithCarry(r.A, r.C, r)}),
  0x8A to Operation("ADC A,D", 0, 4, {r, _, _ -> r.A = addWithCarry(r.A, r.D, r)}),
  0x8B to Operation("ADC A,E", 0, 4, {r, _, _ -> r.A = addWithCarry(r.A, r.E, r)}),
  0x8C to Operation("ADC A,H", 0, 4, {r, _, _ -> r.A = addWithCarry(r.A, r.H, r)}),
  0x8D to Operation("ADC A,L", 0, 4, {r, _, _ -> r.A = addWithCarry(r.A, r.L, r)}),
  0x8E to Operation("ADC A,(HL)", 0, 8, {r, m, _ -> r.A = addWithCarry(r.A, m.readByte(r.HL), r)}),
  0xCE to Operation("ADC A,n", 1, 8, {r, _, a -> r.A = addWithCarry(r.A, a[0], r)}),

  0x97 to Operation("SUB A,A", 0, 4, {r, _, _ -> r.A = subtract(r.A, r.A, r)}),
  0x90 to Operation("SUB A,B", 0, 4, {r, _, _ -> r.A = subtract(r.A, r.B, r)}),
  0x91 to Operation("SUB A,C", 0, 4, {r, _, _ -> r.A = subtract(r.A, r.C, r)}),
  0x92 to Operation("SUB A,D", 0, 4, {r, _, _ -> r.A = subtract(r.A, r.D, r)}),
  0x93 to Operation("SUB A,E", 0, 4, {r, _, _ -> r.A = subtract(r.A, r.E, r)}),
  0x94 to Operation("SUB A,H", 0, 4, {r, _, _ -> r.A = subtract(r.A, r.H, r)}),
  0x95 to Operation("SUB A,L", 0, 4, {r, _, _ -> r.A = subtract(r.A, r.L, r)}),
  0x96 to Operation("SUB A,(HL)", 0, 8, {r, m, _ -> r.A = subtract(r.A, m.readByte(r.HL), r)}),
  0xD6 to Operation("SUB A,n", 1, 8, {r, _, a -> r.A = subtract(r.A, a[0], r)}),

  0x9F to Operation("SBC A,A", 0, 4, {r, _, _ -> r.A = subtractWithCarry(r.A, r.A, r) }),
  0x98 to Operation("SBC A,B", 0, 4, {r, _, _ -> r.A = subtractWithCarry(r.A, r.B, r)}),
  0x99 to Operation("SBC A,C", 0, 4, {r, _, _ -> r.A = subtractWithCarry(r.A, r.C, r)}),
  0x9A to Operation("SBC A,D", 0, 4, {r, _, _ -> r.A = subtractWithCarry(r.A, r.D, r)}),
  0x9B to Operation("SBC A,E", 0, 4, {r, _, _ -> r.A = subtractWithCarry(r.A, r.E, r)}),
  0x9C to Operation("SBC A,H", 0, 4, {r, _, _ -> r.A = subtractWithCarry(r.A, r.H, r)}),
  0x9D to Operation("SBC A,L", 0, 4, {r, _, _ -> r.A = subtractWithCarry(r.A, r.L, r)}),
  0x9E to Operation("SBC A,(HL)", 0, 8, {r, m, _ -> r.A = subtractWithCarry(r.A, m.readByte(r.HL), r)}),
  // opcode missing in CPU manual? http://pastraiser.com/cpu/gameboy/gameboy_opcodes.html lists as 0xDE
  0xDE to Operation("SBC A,n", 1, 8, {r, _, a -> r.A = subtractWithCarry(r.A, a[0], r)}),

  0xA7 to Operation("AND A,A", 0, 4, {r, _, _ -> r.A = and(r.A, r.A, r)}),
  0xA0 to Operation("AND A,B", 0, 4, {r, _, _ -> r.A = and(r.A, r.B, r)}),
  0xA1 to Operation("AND A,C", 0, 4, {r, _, _ -> r.A = and(r.A, r.C, r)}),
  0xA2 to Operation("AND A,D", 0, 4, {r, _, _ -> r.A = and(r.A, r.D, r)}),
  0xA3 to Operation("AND A,E", 0, 4, {r, _, _ -> r.A = and(r.A, r.E, r)}),
  0xA4 to Operation("AND A,H", 0, 4, {r, _, _ -> r.A = and(r.A, r.H, r)}),
  0xA5 to Operation("AND A,L", 0, 4, {r, _, _ -> r.A = and(r.A, r.L, r)}),
  0xA6 to Operation("AND A,(HL)", 0, 8, {r, m, _ -> r.A = and(r.A, m.readByte(r.HL), r)}),
  0xE6 to Operation("AND A,n", 1, 8, {r, _, a -> r.A = and(r.A, a[0], r)}),

  0xB7 to Operation("OR A,A", 0, 4, {r, _, _ -> r.A = or(r.A, r.A, r)}),
  0xB0 to Operation("OR A,B", 0, 4, {r, _, _ -> r.A = or(r.A, r.B, r)}),
  0xB1 to Operation("OR A,C", 0, 4, {r, _, _ -> r.A = or(r.A, r.C, r)}),
  0xB2 to Operation("OR A,D", 0, 4, {r, _, _ -> r.A = or(r.A, r.D, r)}),
  0xB3 to Operation("OR A,E", 0, 4, {r, _, _ -> r.A = or(r.A, r.E, r)}),
  0xB4 to Operation("OR A,H", 0, 4, {r, _, _ -> r.A = or(r.A, r.H, r)}),
  0xB5 to Operation("OR A,L", 0, 4, {r, _, _ -> r.A = or(r.A, r.L, r)}),
  0xB6 to Operation("OR A,(HL)", 0, 8, {r, m, _ -> r.A = or(r.A, m.readByte(r.HL), r)}),
  0xF6 to Operation("OR A,n", 1, 8, {r, _, a -> r.A = or(r.A, a[0], r)}),

  0xAF to Operation("XOR A,A", 0, 4, {r, _, _ -> r.A = xor(r.A, r.A, r)}),
  0xA8 to Operation("XOR A,B", 0, 4, {r, _, _ -> r.A = xor(r.A, r.B, r)}),
  0xA9 to Operation("XOR A,C", 0, 4, {r, _, _ -> r.A = xor(r.A, r.C, r)}),
  0xAA to Operation("XOR A,D", 0, 4, {r, _, _ -> r.A = xor(r.A, r.D, r)}),
  0xAB to Operation("XOR A,E", 0, 4, {r, _, _ -> r.A = xor(r.A, r.E, r)}),
  0xAC to Operation("XOR A,H", 0, 4, {r, _, _ -> r.A = xor(r.A, r.H, r)}),
  0xAD to Operation("XOR A,L", 0, 4, {r, _, _ -> r.A = xor(r.A, r.L, r)}),
  0xAE to Operation("XOR A,(HL)", 0, 8, {r, m, _ -> r.A = xor(r.A, m.readByte(r.HL), r)}),
  0xEE to Operation("XOR A,n", 1, 8, {r, _, a -> r.A = xor(r.A, a[0], r)}),

  // Compare is just A - n but the result is discarded
  0xBF to Operation("CP A,A", 0, 4, {r, _, _ -> subtract(r.A, r.A, r)}),
  0xB8 to Operation("CP A,B", 0, 4, {r, _, _ -> subtract(r.A, r.B, r)}),
  0xB9 to Operation("CP A,C", 0, 4, {r, _, _ -> subtract(r.A, r.C, r)}),
  0xBA to Operation("CP A,D", 0, 4, {r, _, _ -> subtract(r.A, r.D, r)}),
  0xBB to Operation("CP A,E", 0, 4, {r, _, _ -> subtract(r.A, r.E, r)}),
  0xBC to Operation("CP A,H", 0, 4, {r, _, _ -> subtract(r.A, r.H, r)}),
  0xBD to Operation("CP A,L", 0, 4, {r, _, _ -> subtract(r.A, r.L, r)}),
  0xBE to Operation("CP A,(HL)", 0, 8, {r, m, _ -> subtract(r.A, m.readByte(r.HL), r)}),
  0xFE to Operation("CP A,n", 1, 8, {r, _, a -> subtract(r.A, a[0], r)}),

  0x3C to Operation("INC A", 0, 4, {r, _, _ -> r.A = inc(r.A, r)}),
  0x04 to Operation("INC B", 0, 4, {r, _, _ -> r.B = inc(r.B, r)}),
  0x0C to Operation("INC C", 0, 4, {r, _, _ -> r.C = inc(r.C, r)}),
  0x14 to Operation("INC D", 0, 4, {r, _, _ -> r.D = inc(r.D, r)}),
  0x1C to Operation("INC E", 0, 4, {r, _, _ -> r.E = inc(r.E, r)}),
  0x24 to Operation("INC H", 0, 4, {r, _, _ -> r.H = inc(r.H, r)}),
  0x2C to Operation("INC L", 0, 4, {r, _, _ -> r.L = inc(r.L, r)}),
  0x34 to Operation("INC (HL)", 0, 12, {r, m, _ -> m.writeByte(r.HL, inc(m.readByte(r.HL), r))}),

  0x3D to Operation("DEC A", 0, 4, {r, _, _ -> r.A = dec(r.A, r)}),
  0x05 to Operation("DEC B", 0, 4, {r, _, _ -> r.B = dec(r.B, r)}),
  0x0D to Operation("DEC C", 0, 4, {r, _, _ -> r.C = dec(r.C, r)}),
  0x15 to Operation("DEC D", 0, 4, {r, _, _ -> r.D = dec(r.D, r)}),
  0x1D to Operation("DEC E", 0, 4, {r, _, _ -> r.E = dec(r.E, r)}),
  0x25 to Operation("DEC H", 0, 4, {r, _, _ -> r.H = dec(r.H, r)}),
  0x2D to Operation("DEC L", 0, 4, {r, _, _ -> r.L = dec(r.L, r)}),
  0x35 to Operation("DEC (HL)", 0, 12, {r, m, _ -> m.writeByte(r.HL, dec(m.readByte(r.HL), r))})
)

fun add(n1: Int, n2: Int, r: Registers): Int {

  val result = maskedAdd(n1, n2)

  r.clearFlag(Flag.SUBTRACT)
  r.setFlagFromBool(Flag.ZERO, result == 0)
  r.setFlagFromBool(Flag.HALF_CARRY,(n1 and 0x0F) + (n2 and 0x0F) > 0x0F)
  r.setFlagFromBool(Flag.CARRY, (n1 + n2) > 0xFF)

  return result
}

fun addWithCarry(n1: Int, n2: Int, r: Registers): Int {

  val carry = r.getFlag(Flag.CARRY)
  val result = maskedAdd(n1, n2, carry)

  r.clearFlag(Flag.SUBTRACT)
  r.setFlagFromBool(Flag.ZERO, result == 0)
  r.setFlagFromBool(Flag.HALF_CARRY,(n1 and 0x0F) + (n2 and 0x0F) + carry > 0x0F)
  r.setFlagFromBool(Flag.CARRY, (n1 + n2 + carry) > 0xFF)

  return result
}

fun maskedAdd(vararg n: Int): Int {
  return (n.sum()) and 0xFF
}

fun subtract(n1: Int, n2: Int, r: Registers): Int {

  r.setFlag(Flag.SUBTRACT)
  r.setFlagFromBool(Flag.ZERO, (n1 - n2) and 0xFF == 0)
  r.setFlagFromBool(Flag.HALF_CARRY, 0x0F and n2 > 0x0F and n1)
  r.setFlagFromBool(Flag.CARRY, n2 > n1)

  return (n1 - n2) % 0xFF
}

fun subtractWithCarry(n1: Int, n2: Int, r: Registers): Int {

  val carry = r.getFlag(Flag.CARRY)

  r.setFlag(Flag.SUBTRACT)
  r.setFlagFromBool(Flag.ZERO, (n1 - n2 - carry) and 0xFF == 0)
  r.setFlagFromBool(Flag.HALF_CARRY, 0x0F and (n2 + carry) > 0x0F and n1)
  r.setFlagFromBool(Flag.CARRY, (n2 + carry) > n1)

  return (n1 - n2 - carry) % 0xFF
}

fun and(n1: Int, n2: Int, r: Registers): Int {

  val result = n1 and n2

  r.setFlagFromBool(Flag.ZERO, result == 0)
  r.clearFlag(Flag.SUBTRACT)
  r.setFlag(Flag.HALF_CARRY)
  r.clearFlag(Flag.CARRY)

  return result
}

fun or(n1: Int, n2: Int, r: Registers): Int {

  val result = n1 or n2

  r.setFlagFromBool(Flag.ZERO, result == 0)
  r.clearFlag(Flag.SUBTRACT)
  r.clearFlag(Flag.HALF_CARRY)
  r.clearFlag(Flag.CARRY)

  return result
}

fun xor(n1: Int, n2: Int, r: Registers): Int {

  val result = n1 xor n2

  r.setFlagFromBool(Flag.ZERO, result == 0)
  r.clearFlag(Flag.SUBTRACT)
  r.clearFlag(Flag.HALF_CARRY)
  r.clearFlag(Flag.CARRY)

  return result
}

fun inc(n: Int, r: Registers): Int {

  val result = (n + 1) and 0xFF

  r.setFlagFromBool(Flag.ZERO, result == 0)
  r.clearFlag(Flag.SUBTRACT)
  r.setFlagFromBool(Flag.HALF_CARRY, (0x0F and result) < (0x0F and n))

  return result
}

fun dec(n: Int, r: Registers): Int {

  val result = (n - 1) and 0xFF

  r.setFlagFromBool(Flag.ZERO, result == 0)
  r.clearFlag(Flag.SUBTRACT)
  r.setFlagFromBool(Flag.HALF_CARRY, (0x0F and n) == 0)

  return result
}