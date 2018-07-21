package ram

class Ram {

  // 64K memory area
  var ram = IntArray(0xFFFF)

  fun readByte(address: Int) : Int {
    return ram[address]
  }

  fun writeByte(address: Int, data: Int) {
    ram[address] = data
  }

  fun load(rom: ByteArray) {

    rom.forEachIndexed{i, b ->
      ram[i] = b.toInt() and 0xFF
    }
  }

}