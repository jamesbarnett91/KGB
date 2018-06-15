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


}