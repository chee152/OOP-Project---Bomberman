package uet.oop.bomberman.entities.character.enemy;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.collision.Collision;
import uet.oop.bomberman.entities.character.enemy.AI.AINormal;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.util.Objects;

public class Minvo extends Enemy{
    public Minvo(int xUnit, int yUnit, Image img)
    {
        super(xUnit, yUnit, img);
        sprite = Sprite.minvo_right1;
        AI = new AINormal(this);
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
            sprite = Sprite.minvo_dead;
        }
        else
        {
            switch (direction)
            {
                case 0:
                case 1:
                    if (moving)
                        sprite = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate, time);
                    else
                        sprite = Sprite.minvo_right1;
                    break;
                case 2:
                case 3:
                    if (moving)
                        sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate, time);
                    else
                        sprite = Sprite.minvo_right1;
                    break;
            }
        }
    }

    public void calculateMove()
    {
        direction = AI.calculateMovingDirection();
        if (!this.isDie)
        {
            if (direction == 0)
            {
                if (canGoUp(x, y)) {
                    y -= speedMinvo;
                }
            }

            else if (direction == 1) {
                if (canGoRight(x, y)) {
                    x += speedMinvo;
                }
            }
            else if (direction == 2) {
                if (canGoDown(x, y)) {
                    y += speedMinvo;
                }
            }
            else if (direction == 3) {
                if (canGoLeft(x, y)) {
                    x -= speedMinvo;
                }
            }
        }
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage(img, x, y);
    }
}
