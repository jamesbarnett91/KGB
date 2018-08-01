package gpu

import java.awt.Color
import java.awt.image.BufferedImage

interface Gpu {
  fun setPixel(x: Int, y: Int, colour: Color)
  fun getFrame(): BufferedImage
}