package cpu

import BitManipulation as bm

class Registers {

  // General purpose registers
  var B: Int = 0
    set(value) {
      bm.validateUnsigned8Bit(value)
      field = value
    }
  var C: Int = 0
    set(value) {
      bm.validateUnsigned8Bit(value)
      field = value
    }
  var D: Int = 0
    set(value) {
      bm.validateUnsigned8Bit(value)
      field = value
    }
  var E: Int = 0
    set(value) {
      bm.validateUnsigned8Bit(value)
      field = value
    }
  var H: Int = 0
    set(value) {
      bm.validateUnsigned8Bit(value)
      field = value
    }
  var L: Int = 0
    set(value) {
      bm.validateUnsigned8Bit(value)
      field = value
    }

  // Special registers
  var A: Int = 0
    set(value) {
      bm.validateUnsigned8Bit(value)
      field = value
    }
  var F: Int = 0
    set(value) {
      bm.validateUnsigned8Bit(value)
      field = value
    }
  var SP: Int = 0
    set(value) {
      bm.validateUnsigned16Bit(value)
      field = value
    }
  var PC: Int = 0
    set(value) {
      bm.validateUnsigned16Bit(value)
      field = value
    }

  // 16-Bit accessors
  var AF: Int
    get() {
      return bm.bytesToWord(A, F)
    }
    set(value) {
      bm.validateUnsigned16Bit(value)
      A = bm.getMsb(value)
      F = bm.getLsb(value)
    }

  var BC: Int
    get() {
      return bm.bytesToWord(B, C)
    }
    set(value) {
      bm.validateUnsigned16Bit(value)
      B = bm.getMsb(value)
      C = bm.getLsb(value)
    }

  var DE: Int
    get() {
      return bm.bytesToWord(D, E)
    }
    set(value) {
      bm.validateUnsigned16Bit(value)
      D = bm.getMsb(value)
      E = bm.getLsb(value)
    }

  var HL: Int
    get() {
      return bm.bytesToWord(H, L)
    }
    set(value) {
      bm.validateUnsigned16Bit(value)
      H = bm.getMsb(value)
      L = bm.getLsb(value)
    }

  fun getAndDecrementHL(): Int {
    val currentHL = HL
    if(HL > 0x0000) { // TODO - is this correct? Or should it underflow to 0xFFFF?. Also for increment op.
      HL--
    }
    return currentHL
  }

  fun getAndIncrementHL(): Int {
    val currentHL = HL
    if(HL < 0xFFFF) {
      HL++
    }
    return currentHL
  }

  fun decrementAndGetSP(): Int {
    return if(SP > 0x0000) {
      --SP
    } else {
      SP
    }
  }

  fun getAndIncrementSP(): Int {
    val currentSP = SP
    if(SP < 0xFFFF) {
      SP++
    }
    return currentSP
  }

  fun addSignedByteToPC(byte: Int) {
    PC = if (bm.isSignedByteNegative(byte)) {
      (PC - bm.getAbsoluteValue(byte)) and 0xFFFF
    } else {
      (PC + bm.getAbsoluteValue(byte)) and 0xFFFF
    }
  }

  fun getFlag(flag: Flag): Int {
    return (F shr flag.bitPosition) and 0x01
  }

  fun setFlag(flag: Flag) {
    F = F or (1 shl flag.bitPosition)
  }

  fun clearFlag(flag: Flag) {
    F = F and (1 shl flag.bitPosition).inv()
  }

  fun setFlagFromBool(flag: Flag, isSet: Boolean) {
    if(isSet) {
      setFlag(flag)
    }
    else {
      clearFlag(flag)
    }
  }

  fun clearFlags() {
    F = 0x00
  }

  enum class Flag(val bitPosition: Int) {
    ZERO(7),
    SUBTRACT(6),
    HALF_CARRY(5),
    CARRY(4),
  }

}