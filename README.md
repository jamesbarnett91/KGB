# KGB - Kotlin Game Boy
A Game Boy emulator (eventually...) written in Kotlin
<p align="center">
  <img alt="Screenshot" src="https://jamesbarnett.io/files/kgb/kgb-1.png">
</p>

# Features
- Steppable CPU execution
- CPU register + flag inspector
- Ram dump

# Implementation Status
All non interrupt related opcodes implemented.
Simple GPU generating test pattern.
- [ ] CPU
  - [X] Registers
  - [X] Flags
  - [X] Opcodes
  - [ ] Interrupts
  - [ ] Timers
- [ ] GPU
  - [ ] Pixel pipeline
  - [ ] Sprites
- [ ] MMU
  - [ ] DMA
  - [ ] Bank switching
- [ ]  I/O
  - [ ] Controller inputs
  - [ ] Sound
