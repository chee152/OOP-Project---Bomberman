package uet.oop.bomberman.entities.tile.destroyable;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends DestroyableTile {


    public void setExploded(boolean exploded) {
        this.exploding = exploded;
    }

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

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