package cpu

class Registers {

  // General purpose registers
  var B: Int = 0
    set(value) {
      validateUnsigned8Bit(value)
      field = value
    }
  var C: Int = 0
    set(value) {
      validateUnsigned8Bit(value)
      field = value
    }
  var D: Int = 0
    set(value) {
      validateUnsigned8Bit(value)
      field = value
    }
  var E: Int = 0
    set(value) {
      validateUnsigned8Bit(value)
      field = value
    }
  var H: Int = 0
    set(value) {
      validateUnsigned8Bit(value)
      field = value
    }
  var L: Int = 0
    set(value) {
      validateUnsigned8Bit(value)
      field = value
    }

  // Special registers
  var A: Int = 0
    set(value) {
      validateUnsigned8Bit(value)
      field = value
    }
  var F: Int = 0
    set(value) {
      validateUnsigned8Bit(value)
      field = value
    }
  var SP: Int = 0
    set(value) {
      validateUnsigned16Bit(value)
      field = value
    }
  var PC: Int = 0
    set(value) {
      validateUnsigned16Bit(value)
      field = value
    }

  // 16-Bit accessors
  var AF: Int
    get() {
      return bytesToWord(A, F)
    }
    set(value) {
      validateUnsigned16Bit(value)
      A = getMsb(value)
      F = getLsb(value)
    }

  var BC: Int
    get() {
      return bytesToWord(B, C)
    }
    set(value) {
      validateUnsigned16Bit(value)
      B = getMsb(value)
      C = getLsb(value)
    }

  var DE: Int
    get() {
      return bytesToWord(D, E)
    }
    set(value) {
      validateUnsigned16Bit(value)
      D = getMsb(value)
      E = getLsb(value)
    }

  var HL: Int
    get() {
      return bytesToWord(H, L)
    }
    set(value) {
      validateUnsigned16Bit(value)
      H = getMsb(value)
      L = getLsb(value)
    }

  private fun bytesToWord(msb: Int, lsb: Int): Int {
    return msb.shl(8) + lsb
  }

  private fun getMsb(value: Int): Int {
    return value.shr(8)
  }

  private fun getLsb(value: Int): Int {
    return value.and(0xFF)
  }

  private fun validateUnsigned8Bit(value: Int) {
    if(value < 0 || value > 255) {
      throw IllegalArgumentException("Value $value is not an unsigned 8-Bit value")
    }
  }

  private fun validateUnsigned16Bit(value: Int) {
    if(value < 0 || value > 65535) {
      throw IllegalArgumentException("Value $value is not an unsigned 16-Bit value")
    }
  }

}