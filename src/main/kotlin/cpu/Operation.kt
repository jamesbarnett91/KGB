package cpu

import ram.Ram

class Operation(val name: String, val length: Int, val cycles: Int, val command: (Registers, Ram, List<Int>) -> Unit)