package uet.oop.bomberman.entities.character.enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.character.enemy.AI.AILow;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.collision.Collision;
import java.io.IOException;
import java.util.Objects;

public class Doll extends Enemy{
    private int count = 0;

    public Doll(int xUnit, int yUnit, Image img)
    {
        super(xUnit,yUnit,img);
        sprite = Sprite.balloom_left1;
        AI = new AILow();
    }
    public void update() throws IOException {
        if (Objects.requireNonNull(Game.getBomber()).isAlive())
        {
            killBomber();
        }
        if (this.isDie())
        {
            afterKill();
        }
        chooseSprite();
        animate();
        calculateMove();
        setImg(sprite.getFxImage());
    }


    public void killBomber()
    {
        if (Objects.requireNonNull(Game.getBomber()).isAlive())
        {
            if (Collision.CheckCollision(this, Objects.requireNonNull(Game.getBomber())))
            {
                Game.getBomber().setAlive(false);
            }
        }
    }
    public void chooseSprite()
    {
        if (this.isDie())
        {
            sprite = Sprite.doll_dead;
        }
        else
        {
            switch (direction)
            {
                case 0: //bottom
                case 1:
                    sprite = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animate, time);
                    break;
                case 2: //top
                case 3:
                    sprite = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animate, time);
                    break;
            }
        }
    }
    //Balloom di chuyển ngẫu nhiên với vận tốc cố định
    private void calculateMove()
    {
        if (!this.isDie())
        {
            count++;
            if (count >= 0 && count < 150  )
            {
                if (canGoRight(x,y))
                {
                    x+=speedDoll;
                    direction = 1;
                }
            }
            if (count >= 150 && count < 300)
            {
                if (canGoLeft(x,y))
                {
                    x -= speedDoll;
                    direction = 3;
                }
            }
            if (count >= 300 && count < 450)
            {
                if (canGoUp(x,y))
                {
                    y -= speedDoll;
                    direction = 0;
                }
            }
            if (count >= 450 && count < 600)
            {
                if (canGoDown(x,y))
                {
                    y += speedDoll;
                    direction = 2;
                }
            }
            if (count >= 600)
            {
                AI.calculateMovingDirection();
            }


        }
    }





    public void render(GraphicsContext gc)
    {
        gc.drawImage(img, x, y);
    }
}
