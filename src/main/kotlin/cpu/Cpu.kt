package cpu

import cpu.opcodes.*
import ram.Ram

class Cpu {

  val registers = Registers()
  val ram = Ram()

  var opcodes: Map<Int, Operation>
  init {
    val commandGroups: MutableMap<Int, Operation> = mutableMapOf()
    commandGroups.putAll(loads8Bit)
    commandGroups.putAll(loads16Bit)
    commandGroups.putAll(arithmetic8Bit)
    commandGroups.putAll(arithmetic16Bit)
    commandGroups.putAll(misc)
    opcodes = commandGroups.toMap()
  }

}