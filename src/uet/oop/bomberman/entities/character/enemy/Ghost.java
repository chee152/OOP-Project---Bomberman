package uet.oop.bomberman.entities.character.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.collision.Collision;
import uet.oop.bomberman.entities.character.enemy.AI.AINormal;
import uet.oop.bomberman.entities.tile.normal.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.util.Objects;

public class Ghost extends  Enemy{
    public Ghost(int xUnit, int yUnit, Image img)
    {
        super(xUnit, yUnit, img);
        sprite = Sprite.ghost;
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

            sprite = Sprite.ghost;

    }

    public void calculateMove()
    {
        direction = AI.calculateMovingDirection();
        if (!this.isDie)
        {
            if (direction == 0)
            {
                if (canGhostGoUp(x, y)) {
                    y -= speedMinvo;
                }
            }

            else if (direction == 1) {
                if (canGhostGoRight(x, y)) {
                    x += speedMinvo;
                }
            }
            else if (direction == 2) {
                if (canGhostGoDown(x, y)) {
                    y += speedMinvo;
                }
            }
            else if (direction == 3) {
                if (canGhostGoLeft(x, y)) {
                    x -= speedMinvo;
                }
            }
        }
    }
    protected boolean canGhostGoLeft(int xpos, int ypos)
    {
        x1_temp = (ypos + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        y1_temp = (xpos - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

        x2_temp = (ypos + SIZE - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        y2_temp = (xpos - pixel)/Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

        return !(getImpassableEntityAt(y1_temp, x1_temp) instanceof Wall
                && getImpassableEntityAt(y2_temp,x2_temp) instanceof Wall);
    }

    protected boolean canGhostGoRight (int xpos, int ypos)
    {
        x1_temp = (ypos + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        y1_temp = (xpos + SIZE + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

        x2_temp = (ypos + SIZE - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        y2_temp = (x + SIZE + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

        return! (getImpassableEntityAt(y1_temp, x1_temp) instanceof Wall
                && getImpassableEntityAt(y2_temp,x2_temp) instanceof Wall);
    }

    protected boolean canGhostGoDown(int xpos, int ypos) {
        x1_temp = (ypos + SIZE + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        y1_temp = (xpos + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

        x2_temp = (ypos + SIZE + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        y2_temp = (xpos + SIZE - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

        return !( getImpassableEntityAt(y1_temp, x1_temp) instanceof Wall
                && getImpassableEntityAt(y2_temp,x2_temp) instanceof Wall);
    }

    protected boolean canGhostGoUp(int xpos, int ypos) {
        x1_temp = (ypos - pixel) /  Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        y1_temp = (xpos + pixel) /Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

        x2_temp = (ypos - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
        y2_temp = (xpos + SIZE - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

        return !(getImpassableEntityAt(y1_temp, x1_temp) instanceof Wall
                && getImpassableEntityAt(y2_temp,x2_temp) instanceof Wall);
    }


    public void render(GraphicsContext gc)
    {
        gc.drawImage(img, x, y);
    }

}
