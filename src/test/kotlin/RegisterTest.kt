import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import java.lang.IllegalArgumentException

class RegisterTest : StringSpec({

  "Test 16-Bit accessors" {
    val reg = Registers()

    reg.AF = 0xF83C
    reg.AF shouldBe 0xF83C
    reg.A shouldBe 0xF8
    reg.F shouldBe 0x3C

    reg.BC = 0xF83C
    reg.BC shouldBe 0xF83C
    reg.B shouldBe 0xF8
    reg.C shouldBe 0x3C

    reg.DE = 0xF83C
    reg.DE shouldBe 0xF83C
    reg.D shouldBe 0xF8
    reg.E shouldBe 0x3C

    reg.HL = 0xF83C
    reg.HL shouldBe 0xF83C
    reg.H shouldBe 0xF8
    reg.L shouldBe 0x3C

  }

  "Test 8-Bit register validation" {
    val reg = Registers()
    shouldThrow<IllegalArgumentException> {
      reg.A = 0xFFFF
      Any()
    }
    shouldThrow<IllegalArgumentException> {
      reg.F = 0xFFFF
      Any()
    }
    shouldThrow<IllegalArgumentException> {
      reg.B = 0xFFFF
      Any()
    }
    shouldThrow<IllegalArgumentException> {
      reg.C = 0xFFFF
      Any()
    }
    shouldThrow<IllegalArgumentException> {
      reg.D = 0xFFFF
      Any()
    }
    shouldThrow<IllegalArgumentException> {
      reg.E = 0xFFFF
      Any()
    }
    shouldThrow<IllegalArgumentException> {
      reg.H = 0xFFFF
      Any()
    }
    shouldThrow<IllegalArgumentException> {
      reg.L = 0xFFFF
      Any()
    }
  }

  "Test 16-Bit register validation" {
    val reg = Registers()
    shouldThrow<IllegalArgumentException> {
      reg.AF = 0xFFFFFF
      Any()
    }
    shouldThrow<IllegalArgumentException> {
      reg.BC = 0xFFFFFF
      Any()
    }
    shouldThrow<IllegalArgumentException> {
      reg.DE = 0xFFFFFF
      Any()
    }
    shouldThrow<IllegalArgumentException> {
      reg.HL = 0xFFFFFF
      Any()
    }
    shouldThrow<IllegalArgumentException> {
      reg.SP = 0xFFFFFF
      Any()
    }
    shouldThrow<IllegalArgumentException> {
      reg.PC = 0xFFFFFF
      Any()
    }

  }

  "Test registers" {
    val reg = Registers()

    reg.A = 0xA0
    reg.A shouldBe 0xA0
    reg.F = 0xA1
    reg.F shouldBe 0xA1

    reg.B = 0xA2
    reg.B shouldBe 0xA2
    reg.C = 0xA3
    reg.C shouldBe 0xA3
    reg.D = 0xA4
    reg.D shouldBe 0xA4
    reg.E = 0xA5
    reg.E shouldBe 0xA5
    reg.H = 0xA6
    reg.H shouldBe 0xA6
    reg.L = 0xA7
    reg.L shouldBe 0xA7

    reg.SP = 0xAAFE
    reg.SP shouldBe 0xAAFE

    reg.PC = 0xAAFE
    reg.PC shouldBe 0xAAFE
  }

})