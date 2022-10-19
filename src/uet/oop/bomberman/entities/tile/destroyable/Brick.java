package uet.oop.bomberman.entities.tile.destroyable;
import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends DestroyableTile {
    public Brick(int x, int y, Image img)
    {
        super(x,y,img);
    }
    public void setExplode(boolean explode)
    {
        this.explode = explode;
    }

    @Override
    public void update() {
        handleBrick();
        chooseSprite();
        animate();
        setImg(sprite.getFxImage());
    }
    public void handleBrick(){
        if (!destroyed) {
            timeToDisappear++;
            if (timeToDisappear < Game.TIME_TO_DISAPPEAR) {
                explode = true;
            } else {
                explode = false;
                timeToDisappear = 0;
                destroyed = true;
            }
        }
    }
    public void animate()
    {
        if (animate < 7500) animate++;
        else animate = 0;
    }
    public void chooseSprite()
    {
        int time = Game.TIME_TO_DISAPPEAR;
        if (explode)
        {
            sprite = Sprite.movingSprite(
                    Sprite.brick_exploded,
                    Sprite.brick_exploded1,
                    Sprite.brick_exploded2,
                    animate,
                    time);
        }
        else {
            sprite = Sprite.brick;
        }
    }
}
