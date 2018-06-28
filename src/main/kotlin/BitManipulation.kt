object BitManipulation {

  fun bytesToWord(msb: Int, lsb: Int): Int {
    return msb.shl(8) + lsb
  }

  fun argsToWord(args: IntArray): Int {
    return bytesToWord(args[1], args[0])
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

  fun isSignedBitNegative(value: Int): Boolean {
    return value and (1 shl 7) != 0
  }

  fun getAbsoluteValue(value: Int ): Int{
    return if(isSignedBitNegative(value)) {
      0x0100 - value // 1 00000000 - 1xxxxxx = value, due to 2s compliment
    } else {
      value
    }
  }
}