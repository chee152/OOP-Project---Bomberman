package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.collision.Collision;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;


public abstract class AnimatedEntity extends Entity{
    public AnimatedEntity(int xUnit, int yUnit, Image img)
    {
        super(xUnit, yUnit, img);
    }

    protected int animate = 0;
    protected int time = 36;
    protected boolean moving = true;
    protected final int MAX_ANIMATE = 7500;
    protected final int SIZE = Sprite.SCALED_SIZE - 6;


    //check va chạm với bomb
    public boolean bombCollision(Bomb bomb, AnimatedEntity animatedEntity)
    {
        if (bomb == null||animatedEntity == null) return false;
        //tính khoảng cách từ bom đến những AnimatedEntity
        int dx = Math.abs(bomb.getX() - animatedEntity.getX());
        int dy = Math.abs(bomb.getY() - animatedEntity.getY());

        //check trạng thái của Bomber, nếu Bomber nằm trong vùng bomb nổ
        //thì Bomber chết và ngược lại
        if (!(0<=dx && dx <= Sprite.SCALED_SIZE && dy>=0 && dy<=Sprite.SCALED_SIZE))
        {
            bomb.canPass = false;
        }
        return false;
    }

    public abstract void kill();
    protected abstract void afterKill();
    protected void animate()
    {
        if (animate < MAX_ANIMATE) animate++;
        else animate = 0;
    }

}

