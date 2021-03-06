package ghostgame.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
  * Kelas Text yang merepresentasikan teks yang akan ditampilkan pada layar.
  * @author Erick Wijaya - 13515057
  */

public class Text {
  
  /**
    * Menampilkan teks pada layar.
    * @param g Nilai grafik yang mencetak teks.
    * @param text Nilai teks yang akan ditampilkan
    * @param xpos Nilai absis dimana teks akan diletakkan.
    * @param ypos Nilai ordinat dimana teks akan diletakkan.
    * @param center Nilai boolean yang melambangkan apakah teks tersebut ditengah atau tidak.
    * @param c Nilai warna dari teks yang akan ditampilkan.
    * @param font Nilai ukuran dari teks yang akan ditampilkan.
    */
  
  public static void drawString(Graphics g, String text, int xpos, int ypos, boolean center,
      Color c, Font font) {
    g.setColor(c);
    g.setFont(font);
    int x = xpos;
    int y = ypos;
    if (center) {
      FontMetrics fm = g.getFontMetrics(font);
      x = xpos - fm.stringWidth(text) / 2;
      y = (ypos - fm.getHeight() / 2) + fm.getAscent();
    }
    g.drawString(text, x, y);
  }

}