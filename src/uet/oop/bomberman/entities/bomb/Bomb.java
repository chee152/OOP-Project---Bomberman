package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.entities.MovingEntity;
import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.tile.DestroyBrick;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.map.GameMap;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends MovingEntity {
    private long timeToExplode;
    private boolean exploding = false;
    private boolean destroyed = false;
    public boolean canPass = true;

    private final List<Flame> flameList = new ArrayList<>();

    public List<Flame> getFlameList() {
        return flameList;
    }

    public Bomb(int xUnit, int yUnit, Image img)
    {
        super(xUnit, yUnit, img);
        timeToExplode = 0;
        initAndHandleFlame();
    }

    public boolean isExploding() {
        return exploding;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
    private void chooseSprite()
    {
        sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, time);
    }
    public void update()
    {
        handleBomb();
        chooseSprite();
        animate();
        setImg(sprite.getFxImage());
    }
    private void handleBomb()
    {
        if (!destroyed)
        {
            timeToExplode++;
            if (timeToExplode < Game.TIME_TO_EXPLOSION_BOMB)
            {
                exploding = false;
            }
            else{
                if (timeToExplode < Game.TIME_TO_EXPLOSION_BOMB + Game.TIME_TO_DISAPPEAR)
                {
                    exploding = true;
                } else if (timeToExplode > Game.TIME_TO_DISAPPEAR + Game.TIME_TO_EXPLOSION_BOMB) {
                    timeToExplode = 0;
                    destroyed = true;
                }
            }
        }
    }

    protected boolean canGoLeft(int xpos, int ypos)
    {
        x1_temp = (ypos + pixel) / Sprite.SCALED_SIZE;
        y1_temp = (xpos - pixel) / Sprite.SCALED_SIZE;

        x2_temp = (ypos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
        y2_temp = (xpos - pixel)/Sprite.SCALED_SIZE;

        return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
                GameMap.getMap()[x2_temp][y2_temp] != '#';
    }

    protected boolean canGoRight (int xpos, int ypos)
    {
        x1_temp = (ypos + pixel) / Sprite.SCALED_SIZE;
        y1_temp = (xpos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;

        x2_temp = (ypos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;

        return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
                GameMap.getMap()[x2_temp][y2_temp] != '#';
    }

    protected boolean canGoDown(int xpos, int ypos) {
        x1_temp = (ypos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
        y1_temp = (xpos + pixel) / Sprite.SCALED_SIZE;

        x2_temp = (ypos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
        y2_temp = (xpos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;

        return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
                GameMap.getMap()[x2_temp][y2_temp] != '#';
    }

    protected boolean canGoUp(int xpos, int ypos) {
        x1_temp = (ypos - pixel) / Sprite.SCALED_SIZE;
        y1_temp = (xpos + pixel) / Sprite.SCALED_SIZE;

        x2_temp = (ypos - pixel) / Sprite.SCALED_SIZE;
        y2_temp = (xpos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;

        return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
                GameMap.getMap()[x2_temp][y2_temp] != '#';
    }

    //Brick
    protected boolean leftableBrick(int x_pos, int y_pos) {
        x1_temp = (y_pos + pixel) / Sprite.SCALED_SIZE;
        y1_temp = (x_pos - pixel) / Sprite.SCALED_SIZE;

        x2_temp = (y_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x_pos - pixel) / Sprite.SCALED_SIZE;

        if (Game.LayeredEntity.containsKey(Game.generateKey(y1_temp, x1_temp)) ||
                Game.LayeredEntity.containsKey(Game.generateKey(y2_temp, x2_temp))) {
            return !(Game.LayeredEntity.get(Game.generateKey(y1_temp, x1_temp)).peek() instanceof DestroyBrick) &&
                    !(Game.LayeredEntity.get(Game.generateKey(y2_temp, x1_temp)).peek() instanceof DestroyBrick);
        }
        return true;
    }


    protected boolean rightableBrick(int x_pos, int y_pos) {
        x1_temp = (y_pos + pixel) / Sprite.SCALED_SIZE;
        y1_temp = (x_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;

        x2_temp = (y_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;

        if (Game.LayeredEntity.containsKey(Game.generateKey(y1_temp, x1_temp)) ||
                Game.LayeredEntity.containsKey(Game.generateKey(y2_temp, x2_temp))) {
            return !(Game.LayeredEntity.get(Game.generateKey(y1_temp, x1_temp)).peek() instanceof DestroyBrick) &&
                    !(Game.LayeredEntity.get(Game.generateKey(y2_temp, x1_temp)).peek() instanceof DestroyBrick);
        }
        return true;
    }


    protected boolean downableBrick(int x_pos, int y_pos) {
        x1_temp = (y_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
        y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE;

        x2_temp = (y_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;

        if (Game.LayeredEntity.containsKey(Game.generateKey(y1_temp, x1_temp)) ||
                Game.LayeredEntity.containsKey(Game.generateKey(y2_temp, x2_temp))) {
            return !(Game.LayeredEntity.get(Game.generateKey(y1_temp, x1_temp)).peek() instanceof DestroyBrick) &&
                    !(Game.LayeredEntity.get(Game.generateKey(y2_temp, x1_temp)).peek() instanceof DestroyBrick);
        }
        return true;
    }

    protected boolean upableBrick(int x_pos, int y_pos) {
        x1_temp = (y_pos - pixel) / Sprite.SCALED_SIZE;
        y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE;

        x2_temp = (y_pos - pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
        if (Game.LayeredEntity.containsKey(Game.generateKey(y1_temp, x1_temp)) ||
                Game.LayeredEntity.containsKey(Game.generateKey(y2_temp, x2_temp))) {
            return !(Game.LayeredEntity.get(Game.generateKey(y1_temp, x1_temp)).peek() instanceof DestroyBrick) &&
                    !(Game.LayeredEntity.get(Game.generateKey(y2_temp, x1_temp)).peek() instanceof DestroyBrick);
        }
        return true;
    }



    private void initAndHandleFlame()
    {
        flameList.clear();
        Flame newFlame = null;
        for (int i = 0; i<Game.LENGTH_OF_FLAME;i++)
        {
            if (!canGoLeft(x-i*Sprite.SCALED_SIZE,y))
            {
                break;
            }
            newFlame = new Flame(
                    (x-(1+i)*Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE,
                    y/Sprite.SCALED_SIZE,
                    Sprite.explosion_horizontal.getFxImage());
            if (i!= Game.LENGTH_OF_FLAME - 1)
            {
                newFlame.setStatus("horizontal");
            }
            else
            {
                newFlame.setStatus("left");
            }
            flameList.add(newFlame); //cai nay de lam gi
        }

        for (int i = 0; i<Game.LENGTH_OF_FLAME;i++)
        {
            if (!canGoRight(x+i*Sprite.SCALED_SIZE,y))
            {
                break;
            }
            newFlame = new Flame(
                    (x+(1+i)*Sprite.SCALED_SIZE)/Sprite.SCALED_SIZE,
                    y / Sprite.SCALED_SIZE,
                    Sprite.explosion_horizontal.getFxImage());
            if (i!= Game.LENGTH_OF_FLAME - 1)
            {
                newFlame.setStatus("horizontal");
            }
            else
            {
                newFlame.setStatus("right");
            }
            flameList.add(newFlame);
        }
        for (int i = 0; i < Game.LENGTH_OF_FLAME; i++) {
            if (!canGoUp(x, y - i * Sprite.SCALED_SIZE)) {
                break;
            }
            newFlame =
                    new Flame(
                            x / Sprite.SCALED_SIZE,
                            (y - (1 + i) * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE,
                            Sprite.explosion_vertical.getFxImage());
            if (i != Game.LENGTH_OF_FLAME - 1) {
                newFlame.setStatus("vertical");
            } else {
                newFlame.setStatus("top");
            }
            flameList.add(newFlame);
        }

        for (int i = 0; i < Game.LENGTH_OF_FLAME; i++) {
            if (!canGoDown(x, y + i * Sprite.SCALED_SIZE)) {
                break;
            }
            newFlame =
                    new Flame(
                            x / Sprite.SCALED_SIZE,
                            (y + (1 + i) * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE,
                            Sprite.explosion_vertical.getFxImage());
            if (i != Game.LENGTH_OF_FLAME - 1) {
                newFlame.setStatus("vertical");
            } else {
                newFlame.setStatus("down");
            }
            flameList.add(newFlame);
        }
        newFlame =
                new Flame(x / Sprite.SCALED_SIZE,
                        y / Sprite.SCALED_SIZE,
                        Sprite.explosion_vertical.getFxImage());
        newFlame.setStatus("bomb_exploded");
        flameList.add(newFlame);
    }

    public void afterKill()
    {

    }
}
