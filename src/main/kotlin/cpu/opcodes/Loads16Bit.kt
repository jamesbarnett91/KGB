package cpu.opcodes

import cpu.Operation
import cpu.Registers
import cpu.Registers.Flag
import ram.Ram
import BitManipulation as bm

var loads16Bit = mapOf(

  0x01 to Operation("LD BC,nn", 2, 12, {r, _, a -> r.BC = bm.argsToWord(a)}),
  0x11 to Operation("LD DE,nn", 2, 12, {r, _, a -> r.DE = bm.argsToWord(a)}),
  0x21 to Operation("LD HL,nn", 2, 12, {r, _, a -> r.HL = bm.argsToWord(a)}),
  0x31 to Operation("LD SP,nn", 2, 12, {r, _, a -> r.SP = bm.argsToWord(a)}),

  0xF9 to Operation("LD SP,HL", 0, 8, {r, _, _ -> r.SP = r.HL}),

  0xF8 to Operation("LDHL SP,n", 1, 12, {r, _, a ->

    // https://stackoverflow.com/questions/5159603/gbz80-how-does-ld-hl-spe-affect-h-and-c-flags
    // the carry flag is set if there's an overflow from the 7th to 8th bit.
    // the half carry flag is set if there's an overflow from the 3rd into the 4th bit.
    r.clearFlags()

    val absoluteValue = bm.getAbsoluteValue(a[0])

    if (bm.isSignedByteNegative(a[0])) {
      r.setFlagFromBool(Flag.HALF_CARRY, r.SP and 0x0F < absoluteValue and 0x0F)
      r.setFlagFromBool(Flag.CARRY,r.SP and 0xFF < absoluteValue)
      r.HL = r.SP - absoluteValue

    } else {
      r.setFlagFromBool(Flag.HALF_CARRY,(r.SP and 0x0F) + (absoluteValue and 0x0F) > 0x0F)
      r.setFlagFromBool(Flag.CARRY,(r.SP and 0xFF) + absoluteValue > 0xFF)
      r.HL = r.SP + absoluteValue
    }

  }),
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

private fun pop(r: Registers, m: Ram): Int {
  val lsb = m.readByte(r.getAndIncrementSP())
  val msb = m.readByte(r.getAndIncrementSP())
  return bm.bytesToWord(msb, lsb)
}
