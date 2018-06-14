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

}