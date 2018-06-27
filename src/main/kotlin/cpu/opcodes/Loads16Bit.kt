package cpu.opcodes

import cpu.Operation
import cpu.Registers
import ram.Ram
import BitManipulation as bm

// 8-Bit Loads
var loads16Bit = mapOf(

  0x01 to Operation("LD BC,nn", 2, 12, {r, _, a -> r.BC = bm.argsToWord(a)}),
  0x11 to Operation("LD DE,nn", 2, 12, {r, _, a -> r.DE = bm.argsToWord(a)}),
  0x21 to Operation("LD HL,nn", 2, 12, {r, _, a -> r.HL = bm.argsToWord(a)}),
  0x31 to Operation("LD SP,nn", 2, 12, {r, _, a -> r.SP = bm.argsToWord(a)}),

  0xF9 to Operation("LD SP,HL", 0, 8, {r, _, _ -> r.SP = r.HL}),

  0xF8 to Operation("LDHL SP,n", 1, 12, {r, _, a -> r.HL = (r.SP + a[0])}), // TODO - not sure what the flags should be here
  0x08 to Operation("LD (nn),SP", 2, 20, {r, m, a ->
    m.writeByte(bm.argsToWord(a), bm.getLsb(r.SP))
    m.writeByte(bm.argsToWord(a) + 1, bm.getMsb(r.SP))
    // TODO - not sure if the lsb/msb order is right here
  }),

  0xF5 to Operation("PUSH AF", 0, 16, {r, m, _ ->
    m.writeByte(r.decrementAndGetSP(), bm.getMsb(r.AF))
    m.writeByte(r.decrementAndGetSP(), bm.getLsb(r.AF))
  }),
  0xC5 to Operation("PUSH BC", 0, 16, {r, m, _ ->
    m.writeByte(r.decrementAndGetSP(), bm.getMsb(r.BC))
    m.writeByte(r.decrementAndGetSP(), bm.getLsb(r.BC))
  }),
  0xD5 to Operation("PUSH DE", 0, 16, {r, m, _ ->
    m.writeByte(r.decrementAndGetSP(), bm.getMsb(r.DE))
    m.writeByte(r.decrementAndGetSP(), bm.getLsb(r.DE))
  }),
  0xE5 to Operation("PUSH HL", 0, 16, {r, m, _ ->
    m.writeByte(r.decrementAndGetSP(), bm.getMsb(r.HL))
    m.writeByte(r.decrementAndGetSP(), bm.getLsb(r.HL))
  }),

  0xF1 to Operation("POP AF", 0, 12, {r, m, _ -> r.AF = pop(r, m)}),
  0xC1 to Operation("POP BC", 0, 12, {r, m, _ -> r.BC = pop(r, m)}),
  0xD1 to Operation("POP DE", 0, 12, {r, m, _ -> r.DE = pop(r, m)}),
  0xE1 to Operation("POP HL", 0, 12, {r, m, _ -> r.HL = pop(r, m)})

)

fun pop(r: Registers, m: Ram): Int {
  val lsb = m.readByte(r.getAndIncrementSP())
  val msb = m.readByte(r.getAndIncrementSP())
  return bm.bytesToWord(msb, lsb)
}
