import cpu.Cpu
import gpu.Gpu
import java.io.File

class GameBoy(val cpu: Cpu, val gpu: Gpu) {

  fun loadRom(file: File) {
    this.cpu.loadRom(file.readBytes())
  }
}