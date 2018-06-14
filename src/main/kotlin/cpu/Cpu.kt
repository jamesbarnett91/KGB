package cpu

import cpu.opcodes.loads8Bit
import ram.Ram

class Cpu {

  val registers = Registers()
  val ram = Ram()

  var opcodes: Map<Int, Operation>
  init {
    val commandGroups: MutableMap<Int, Operation> = mutableMapOf()
    commandGroups.putAll(loads8Bit)
    opcodes = commandGroups.toMap()
  }

}