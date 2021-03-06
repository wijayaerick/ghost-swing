package ghostgame.states;

import ghostgame.Handler;
import ghostgame.gfx.Assets;
import ghostgame.ui.ClickListener;
import ghostgame.ui.UIimage;
import ghostgame.ui.UIimageButton;
import ghostgame.ui.UImanager;
import java.awt.Graphics;

/**
 * File : WinState.java.
 * Kelas WinState merepresentasikan tampilan win game. 
 * @author Veren Iliana K - 135150578.
 */

public class WinState extends State {

  private UImanager uimanager;

  /**
   * Constructor dengan parameter.
   * @param handler Menghubungkan world dengan win state.
   */

  public WinState(final Handler handler) {
    super(handler);
    uimanager = new UImanager(handler);
    handler.getMouseManager().setUImanager(uimanager);
    
    for (int x = 0; x < handler.getGame().getWidth(); x += 128) {
      for (int y = 0; y < handler.getGame().getHeight(); y += 128) {
        uimanager.addObject(new UIimage((int)x, (int)y, 128, 128, Assets.mainMenuBackground));
      }
    }
    
    uimanager.addObject(new UIimage(64, 0, 512, 512, Assets.winScreen));

    uimanager.addObject(new UIimageButton(425, 400, 192, 64, Assets.btn_back, new ClickListener() {
      @Override
      public void onClick() {
        handler.getMouseManager().setUImanager(null);
        handler.getGame().setState(new MenuState(handler));
      }
    }));
  }
  
  /**
   * Mengupdate kondisi win state setiap satuan waktu.
   */

  @Override
  public void tick() {
    assert (uimanager != null);
    uimanager.tick();
  }

  /**
   * Menampilkan gambar win state sesuai dengan jenisnya.
   */

  @Override
  public void render(Graphics g) {
    assert (uimanager != null);
    uimanager.render(g);
  }

}
