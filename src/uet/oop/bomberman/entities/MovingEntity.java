package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.collision.Collision;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;


public abstract class MovingEntity extends Entity{
    public MovingEntity(int xUnit, int yUnit, Image img)
    {
        super(xUnit, yUnit, img);
    }

    protected int animate = 0;
    protected int time = 36;
    protected boolean moving = true;
    protected final int MAX_ANIMATE = 7500;
    protected final int SIZE = Sprite.SCALED_SIZE - 6;
    protected int x1_temp, x2_temp, y1_temp, y2_temp;
    protected final int pixel = 7;

    //xử lí khi Bomber đi ra sau khi vừa đặt bom (allowedToPassThru)
    public boolean bombCollision(Bomb bomb, MovingEntity movingEntity)
    {
        if (bomb == null|| movingEntity == null) return false;

        //tính khoảng cách từ bom đến những AnimatedEntity
        int dx =  Math.abs(bomb.getX() - movingEntity.getX());
        int dy =  Math.abs(bomb.getY() - movingEntity.getY());

        //bomber đang ở vị trí đặt bomb
        if (!(0<=dx && dx <= Sprite.SCALED_SIZE && dy>=0 && dy<=Sprite.SCALED_SIZE))
        {
            bomb._allowedToPassThru = false; //chặn bomber đi vào bomb
        }
        if (!bomb._allowedToPassThru)
        {
            return Collision.CheckCollision(bomb,movingEntity);
        }
        return false;
    }

    protected abstract void afterKill();
    protected void animate()
    {
        if (animate < MAX_ANIMATE) animate++;
        else animate = 0;
    }

}

