object BitManipulation {

  fun bytesToWord(msb: Int, lsb: Int): Int {
    return msb.shl(8) + lsb
  }

  fun getMsb(value: Int): Int {
    return value.shr(8)
  }

  fun getLsb(value: Int): Int {
    return value.and(0xFF)
  }

  fun validateUnsigned8Bit(value: Int) {
    if(value < 0 || value > 255) {
      throw IllegalArgumentException("Value $value is not an unsigned 8-Bit value")
    }
  }

  fun validateUnsigned16Bit(value: Int) {
    if(value < 0 || value > 65535) {
      throw IllegalArgumentException("Value $value is not an unsigned 16-Bit value")
    }
  }

}