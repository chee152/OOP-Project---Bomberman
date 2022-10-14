package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;


public abstract class AnimatedEntity extends Entity{
    public AnimatedEntity(int xUnit, int yUnit, Image img)
    {
        super(xUnit, yUnit, img);
    }

}
