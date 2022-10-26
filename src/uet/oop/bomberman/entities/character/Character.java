package uet.oop.bomberman.entities.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.tile.normal.Grass;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Character extends MovingEntity {


    protected boolean _exploding = false;
    protected int timeToDisappear = 0;
    protected boolean destroyed = false;

    public boolean isDestroyed() {
        return destroyed;
    }
    public Character(int xUnit, int yUnit, Image img)
    {
        super(xUnit, yUnit, img);
    }

    @Override
    public void afterKill() {
        if (!destroyed) {
            timeToDisappear++;
            if (timeToDisappear < Game.TIME_TO_DISAPPEAR) {
                _exploding = true;
            } else {
                _exploding = false;
                timeToDisappear = 0;
                destroyed = true;
            }
        }
    }
    //Lấy ra entity mà character có thể không đi qua được tại vị trí x y
    protected Entity getImpassableEntityAt(int x, int y)
    {
        Entity entity = null;

        for (Integer value : Game.getLayeredEntitySet())
        {
            if (Game.LayeredEntity.get(value).peek().getX() == x
            && Game.LayeredEntity.get(value).peek().getY() == y )
            {
                return Game.LayeredEntity.get(value).peek();
            }
        }
        for(Entity e : Game.stillObjects)
        {
            if (e.getX() == x && e.getY() == y)
            {
                return e;
            }
        }
        return entity;
    }

    //Kiểm tra xem tại vị trí hiện tại đối tượng có thể đi sang trái được không

    protected boolean canGoLeft(int xpos, int ypos)
    {
        x1_temp = (ypos + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        y1_temp = (xpos - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

        x2_temp = (ypos + SIZE - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        y2_temp = (xpos - pixel)/Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

        return getImpassableEntityAt(y1_temp, x1_temp) instanceof Grass
                && getImpassableEntityAt(y2_temp,x2_temp) instanceof Grass;
    }

    protected boolean canGoRight (int xpos, int ypos)
    {
        x1_temp = (ypos + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        y1_temp = (xpos + SIZE + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

        x2_temp = (ypos + SIZE - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        y2_temp = (x + SIZE + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

        return getImpassableEntityAt(y1_temp, x1_temp) instanceof Grass
                && getImpassableEntityAt(y2_temp,x2_temp) instanceof Grass;
    }

    protected boolean canGoDown(int xpos, int ypos) {
        x1_temp = (ypos + SIZE + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        y1_temp = (xpos + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

        x2_temp = (ypos + SIZE + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        y2_temp = (xpos + SIZE - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

        return getImpassableEntityAt(y1_temp, x1_temp) instanceof Grass
                && getImpassableEntityAt(y2_temp,x2_temp) instanceof Grass;
    }

    protected boolean canGoUp(int xpos, int ypos) {
        x1_temp = (ypos - pixel) /  Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        y1_temp = (xpos + pixel) /Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

        x2_temp = (ypos - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        y2_temp = (xpos + SIZE - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

        return getImpassableEntityAt(y1_temp, x1_temp) instanceof Grass
                && getImpassableEntityAt(y2_temp,x2_temp) instanceof Grass;
    }

}

