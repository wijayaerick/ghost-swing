package ghostgame.tiles;

import ghostgame.gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * File : TileView.java.
 * Kelas TileView merepresentasikan tampilan tile.
 * @author 
 */

public class TileView {
  
  /**
   * Menampilkan gambar sesuai dengan jenisnya.
   * @param g Gambar.
   */
  
  public void render(Tile tile, Graphics g, int x, int y) {
    BufferedImage texture;
    switch (tile.getId()) {
      case 0: texture = Assets.floor; 
      break;
      case 1: texture = Assets.wall; 
      break;
      //case 2: texture = Assets.wall; break;
      default: texture = Assets.floor; 
      break;
    }
    g.drawImage(texture, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
  }
  
}
