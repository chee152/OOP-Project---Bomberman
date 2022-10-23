package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Tile;
import uet.oop.bomberman.graphics.Sprite;


public class DestroyBrick extends Tile {
    protected int animate = 0;
    protected boolean exploding = false;
    protected int timeToDisapear = 0;
    protected boolean destroyed = false;

    public boolean isDestroyed() {
        return destroyed;
    }

    public DestroyBrick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    public void setExploded(boolean exploded) {
        this.exploding = exploded;
    }

   // public Brick(int x, int y, Image img) {
      //  super(x, y, img);
   // }

    @Override
    public void update() {
        handleBrick();
        chooseSprite();
        animate();
        setImg(sprite.getFxImage());
    }



    private void animate() {
        if (animate < 1) animate++;
        else animate = 0;
    }

    private void chooseSprite() {
        int _time = Game.TIME_TO_DISAPPEAR;
        if (exploding) {
            sprite = Sprite.movingSprite(
                    Sprite.brick_exploded,
                    Sprite.brick_exploded1,
                    Sprite.brick_exploded2,
                    animate, _time);
        } else {
            sprite = Sprite.brick;
        }
    }
    private void handleBrick() {
        if (!destroyed) {
            timeToDisapear++;
            if (timeToDisapear < Game.TIME_TO_DISAPPEAR) {
                exploding = true;
            } else {
                exploding = false;
                timeToDisapear = 0;
                destroyed = true;
            }
        }

    }
}
