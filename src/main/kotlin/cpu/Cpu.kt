package cpu

import cpu.opcodes.*
import ram.Ram

class Cpu {

  val registers = Registers()
  val ram = Ram()

  var standardOpcodes: Map<Int, Operation>
  var extendedOpcodes: Map<Int, Operation>
  init {
    val stdCommandGroup: MutableMap<Int, Operation> = mutableMapOf()
    stdCommandGroup.putAll(loads8Bit)
    stdCommandGroup.putAll(loads16Bit)
    stdCommandGroup.putAll(arithmetic8Bit)
    stdCommandGroup.putAll(arithmetic16Bit)
    stdCommandGroup.putAll(misc)
    stdCommandGroup.putAll(rotates)
    standardOpcodes = stdCommandGroup.toMap()

    val extCommandGroup: MutableMap<Int, Operation> = mutableMapOf()
    extCommandGroup.putAll(miscExtended)
    extCommandGroup.putAll(rotatesExtended)
    extendedOpcodes = extCommandGroup.toMap()


  }

}