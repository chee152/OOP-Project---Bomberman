package uet.oop.bomberman.entities.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;

public abstract class Character extends AnimatedEntity {


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
    protected void afterKill() {
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
    protected Entity getImpassableEntityAt(int x, int y)
    {
        Entity entity = null;
        return entity;
    }
}
