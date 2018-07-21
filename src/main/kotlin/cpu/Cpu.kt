package cpu

import cpu.opcodes.*
import ram.Ram
import kotlin.concurrent.thread

class Cpu {

  val registers = Registers()
  val ram = Ram()

  var currentOp: Operation? = null
  var nextOp: Operation? = null

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
    stdCommandGroup.putAll(jumps)
    stdCommandGroup.putAll(calls)
    stdCommandGroup.putAll(restarts)
    stdCommandGroup.putAll(returns)
    standardOpcodes = stdCommandGroup.toMap()

    val extCommandGroup: MutableMap<Int, Operation> = mutableMapOf()
    extCommandGroup.putAll(miscExtended)
    extCommandGroup.putAll(rotatesExtended)
    extCommandGroup.putAll(shiftsExtended)
    extCommandGroup.putAll(generateExtendedBitOps())
    extendedOpcodes = extCommandGroup.toMap()

  }

  fun loadRom(rom: ByteArray) {
    ram.load(rom)
  }

  fun executeNextInstruction() {

    val (op, opArgs) = getNextOp()
    op.command.invoke(registers, ram, opArgs)

    currentOp = op
    nextOp = peekNextOp()
  }

  fun run() {
    thread {
      while(true) {
        executeNextInstruction()
      }
    }

  }

  private fun getNextOp(): Pair<Operation, IntArray> {

    var addr = registers.PC
    val startByte = ram.readByte(addr)
    val op: Operation

    op = if(startByte == 0xCB) {
      extendedOpcodes[ram.readByte(++addr)]!!
    } else {
      standardOpcodes[startByte]!!
    }

    val opLength = op.length
    val opArgs = IntArray(opLength)

    IntRange(0, opLength -1).forEach {i ->
      opArgs[i] = ram.readByte(++addr)
    }

    registers.PC = ++addr

    return Pair(op, opArgs)
  }


  private fun peekNextOp(): Operation {

    val startByte = ram.readByte(registers.PC)
    return if(startByte == 0xCB) {
      extendedOpcodes[ram.readByte(registers.PC + 1)]!!
    } else {
      standardOpcodes[startByte]!!
    }
  }

}