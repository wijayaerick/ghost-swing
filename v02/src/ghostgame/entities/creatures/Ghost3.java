package ghostgame.entities.creatures;

import ghostgame.Handler;

import ghostgame.entities.Entity;
import ghostgame.tiles.Tile;

import java.util.ArrayList;

/** 
  * File : Ghost3.java.
  * Kelas yang merepresentasikan hantu kedua yang ada pada game ini.
  * @author Kevin Iswara - 13515085.
  */

public class Ghost3 extends Ghost {
  
  /**
    * Constructor.
    * @param handler Nilai yang menghubungkan World dengan Entity.
    * @param x Nilai absis (posisi) dari Ghost3.
    * @param y Nilai ordinar (posisi) dari Ghost3.
    */

  public Ghost3(Handler handler, float x, float y) {
    super(handler, x, y);
    health = 2;
    atk = 3;
  }

  /**
    * Fungsi untuk mengubah xmove atau ymove.
    */

  public void changeMovement() {
    xmove = 0;
    ymove = 0;
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    int upInt;
    int downInt;
    int leftInt;
    int rightInt;
    boolean [][] mapTemp = new boolean[handler.getWorld().getWidth()]
        [handler.getWorld().getHeight()];
    for (int i = 0; i < handler.getWorld().getWidth(); i++) {
      for (int j = 0; j < handler.getWorld().getHeight(); j++) {
        mapTemp[i][j] = handler.getWorld().getTile(i,j).isSolid();
      }
    }
    for (Entity temp : handler.getWorld().getEntityManager().getEntities()) {
      mapTemp[(int)(temp.getX() / Tile.TILEWIDTH)][(int)(temp.getY() / Tile.TILEHEIGHT)] = true;
    }
    mapTemp[(int)(handler.getWorld().getEntityManager().getPlayer().getX() / Tile.TILEWIDTH)]
    [(int)(handler.getWorld().getEntityManager().getPlayer().getY() / Tile.TILEHEIGHT)] = false;
    int xplayer;
    int yplayer;
    xplayer = (int)(handler.getWorld().getEntityManager().getPlayer().getX() / Tile.TILEWIDTH);
    yplayer = (int)(handler.getWorld().getEntityManager().getPlayer().getY() / Tile.TILEHEIGHT);
    try {
      if (mapTemp[(int)xpos / Tile.TILEWIDTH][(int)(ypos / Tile.TILEHEIGHT) - 1] == false) {
        upInt = bfs((int)xpos / Tile.TILEWIDTH, (int)(ypos / Tile.TILEHEIGHT) - 1,
            (int)xplayer, (int)yplayer, mapTemp);
      } else {
        upInt = 999;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      upInt = 999;
    }
    try {
      if (mapTemp[(int)xpos / Tile.TILEWIDTH][(int)(ypos / Tile.TILEHEIGHT) + 1] == false) {
        downInt = bfs((int)xpos / Tile.TILEWIDTH, (int)(ypos / Tile.TILEHEIGHT) + 1, (int)xplayer,
            (int)yplayer, mapTemp);
      } else {
        downInt = 999;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      downInt = 999;
    }
    try {
      if (mapTemp[(int)(xpos / Tile.TILEWIDTH) - 1][(int)ypos / Tile.TILEHEIGHT] == false) {
        leftInt = bfs((int)(xpos / Tile.TILEWIDTH) - 1, (int)ypos / Tile.TILEHEIGHT, (int)xplayer,
            (int)yplayer, mapTemp);
      } else {
        leftInt = 999;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      leftInt = 999;
    }
    try {
      if (mapTemp[(int)(xpos / Tile.TILEWIDTH) + 1][(int)ypos / Tile.TILEHEIGHT] == false) {
        rightInt = bfs((int)(xpos / Tile.TILEWIDTH) + 1, (int)ypos / Tile.TILEHEIGHT,
            (int)xplayer, (int)yplayer, mapTemp);
      } else {
        rightInt = 999;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      rightInt = 999;
    }
    if (upInt <= downInt) {
      if (upInt <= leftInt) {
        if (upInt <= rightInt) {
          up = true;
        } else {
          right = true;
        }
      } else {
        if (leftInt <= rightInt) {
          left = true;
        } else {
          right = true;
        }
      }
    } else {
      if (downInt <= leftInt) {
        if (downInt <= rightInt) {
          down = true;
        } else {
          right = true;
        }
      } else {
        if (leftInt <= rightInt) {
          left = true;
        } else {
          right = true;
        }
      }  
    }
    if ((up) && (upInt != 999)) {
      ymove = -speed;
    }
    if ((down) && (downInt != 999)) {
      ymove = speed;
    }
    if ((left) && (leftInt != 999)) {
      xmove = -speed;
    }
    if ((right) && (rightInt != 999)) {
      xmove = speed;
    }
  }

  /**
    * Mengembalikan jumlah jalan terdekat menuju posisi yang akan dituju.
    * @param x absis dri posisi awal.
    * @param y ordinat dari posisi awal.
    * @param playerPositionX absis dari posisi yang akan dituju.
    * @param playerPositionY ordinat dari posisi yang akan dituju.
    * @param map matriks yang berisi boolean yang dapat dilalui dan tidak.
    * @return Nilai jumlah jalan yang dihasilkan.
    */

  private int bfs(int x, int y, int playerPositionX, int playerPositionY, boolean[][] map) {
    ArrayList<Integer> tempX = new ArrayList<Integer>();
    ArrayList<Integer> tempY = new ArrayList<Integer>();
    boolean[][] mapTemp = new boolean [map.length][map[map.length - 1].length];
    for (int i = 0; i < map.length; i++) {
      mapTemp[i][i] = map[i][i];
    }
    ArrayList<Integer> range = new ArrayList<Integer>();
    tempX.add(x);
    tempY.add(y);
    range.add(0);
    int i = 0;
    mapTemp[tempX.get(i)][tempY.get(i)] = true;
    boolean found = false;
    while ((i < tempX.size()) && (!found)) {
      if ((tempX.get(i) != playerPositionX) || (tempY.get(i) != playerPositionY)) {
        if ((tempX.get(i) + 1) < handler.getWorld().getWidth()) {
          if ((mapTemp[tempX.get(i) + 1][tempY.get(i)]) == false) {
            tempX.add(tempX.get(i) + 1);
            tempY.add(tempY.get(i));
            range.add(range.get(i) + 1);
            mapTemp[tempX.get(i) + 1][tempY.get(i)] = true;
          }
        } 
        if ((tempX.get(i) - 1) >= 0) {
          if ((mapTemp[tempX.get(i) - 1][tempY.get(i)]) == false) {
            tempX.add(tempX.get(i) - 1);
            tempY.add(tempY.get(i));
            range.add(range.get(i) + 1);
            mapTemp[tempX.get(i) - 1][tempY.get(i)] = true;
          }
        }
        if ((tempY.get(i) + 1) < handler.getWorld().getHeight()) {
          if ((mapTemp[tempX.get(i)][tempY.get(i) + 1]) == false) {
            tempX.add(tempX.get(i));
            tempY.add(tempY.get(i) + 1);
            range.add(range.get(i) + 1);
            mapTemp[tempX.get(i)][tempY.get(i) + 1] = true;
          }
        }
        if ((tempY.get(i) - 1) >= 0) {
          if ((mapTemp[tempX.get(i)][tempY.get(i) - 1]) == false) {
            tempX.add(tempX.get(i));
            tempY.add(tempY.get(i) - 1);
            range.add(range.get(i) + 1);
            mapTemp[tempX.get(i)][tempY.get(i) - 1] = true;
          }
        }
        i++;
      } else {
        found = true;
      }
    }
    if (found) {
      return range.get(i);
    } else {
      return 999;
    }
  }
}