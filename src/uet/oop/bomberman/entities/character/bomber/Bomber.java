package uet.oop.bomberman.entities.character.bomber;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.ControlKeyboard.Keyboard;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.map.GameMap;

import java.io.IOException;

public class Bomber extends Character {
    private static int bomberSpeed = 2;
    private Sprite prevSprite = Sprite.player_right;
    private boolean alive = true;

    public Bomber(int x, int y, Image img) {
        super(x,y,img);
        sprite = Sprite.player_right;
    }

    public static int getBomberSpeed() {
        return bomberSpeed;
    }

    public static void setBomberSpeed(int bomberSpeed) {
        Bomber.bomberSpeed = bomberSpeed;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public void update() throws IOException {
        if (!isAlive())
        {
            afterKill();
            if (this.isDestroyed())
            {
                resetBomber();
            }
        }
        chooseSprite();
        animate();
        calculateMove();
        setImg(sprite.getFxImage());
    }

    public void resetBomber() throws IOException
    {
        img = Sprite.player_right.getFxImage();
        moving = false;
        setAlive(true);
        GameMap.initMap();
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage(img, x, y);
    }
    public void chooseSprite()
    {
        if (isAlive())
        {
            if (Keyboard.up) {
                sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animate, time);
                prevSprite = Sprite.player_up;
            }
            if (Keyboard.left) {
                sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, time);
                prevSprite = Sprite.player_left;
            }
            if (Keyboard.down) {
                sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, time);
                prevSprite = Sprite.player_down;
            }
            if (Keyboard.right) {
                sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate, time);
                prevSprite = Sprite.player_right;
            }
            if (!Keyboard.up && !Keyboard.left && !Keyboard.down && !Keyboard.right) {
                sprite = prevSprite;
            }
        }
        else {
            sprite = Sprite.movingSprite(Sprite.player_dead1,Sprite.player_dead2,Sprite.player_dead3, animate, Game.TIME_TO_DISAPPEAR);
        }
    }
    public void calculateMove()
    {
        if (isAlive())
        {
            int dx = 0;
            int dy = 0;
            if (Keyboard.up)
            {
                if (canGoUp(x,y))
                {
                    dy--;
                }
            }
            if (Keyboard.down)
            {
                if (canGoDown(x,y))
                {
                    dy++;
                }
            }
            if (Keyboard.left)
            {
                if (canGoLeft(x,y))
                {
                    dx--;
                }
            }
            if (Keyboard.right)
            {
                if (canGoRight(x,y))
                {
                    dx++;
                }
            }

            //di chuyển

            x += dx * bomberSpeed;
            y += dy * bomberSpeed;
        }
    }
}
