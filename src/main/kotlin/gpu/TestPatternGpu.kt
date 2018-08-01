package gpu

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class TestPatternGpu : Gpu {

  private val SCALING_FACTOR = 4

  private val frame = BufferedImage(160 * SCALING_FACTOR, 144 * SCALING_FACTOR, BufferedImage.TYPE_INT_RGB)

  private var lastColour = Color.BLACK

  override fun setPixel(x: Int, y: Int, colour: Color) {
    (0 until SCALING_FACTOR).forEach { xOffset ->
      (0 until SCALING_FACTOR).forEach { yOffset ->
        frame.setRGB((x * SCALING_FACTOR) + xOffset, (y * SCALING_FACTOR) + yOffset, colour.rgb)
      }
    }
  }

  override fun getFrame(): BufferedImage {

    (0 until 144).forEach { y ->

      if(y % 4 == 0) {
        swapColour()
      }

      (0 until 160).forEach { x ->

        if(x % 4 == 0) {
          swapColour()
        }

        setPixel(x, y, lastColour)
      }
    }

    return frame
  }

  private fun swapColour() {
    lastColour = if(lastColour == Color.BLACK) {
      Color.WHITE
    } else {
      Color.BLACK
    }
  }
}