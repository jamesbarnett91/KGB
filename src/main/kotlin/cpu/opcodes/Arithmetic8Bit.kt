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
  0xDE to Operation("SBC A,n", 1, 8, {r, _, a -> r.A = subtractWithCarry(r.A, a[0], r)})

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

