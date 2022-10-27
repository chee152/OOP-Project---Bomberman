package uet.oop.bomberman.entities.character.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemy.AI.AI;

public abstract class Enemy extends Character {

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public int speedBalloom = Game.SPEED_OF_BALLOOM;
    public int speedDoll=Game.SPEED_OF_DOLL;
    public int speedMinvo = Game.SPEED_OF_MINVO;
    public int speedOneal = Game.SPEED_OF_ONEAL;
    protected int direction = -1;
    public boolean isDie = false;
    protected AI AI;

    public boolean isDie()
    {
        return isDie;
    }
    public void enemyDie()
    {
        isDie = true;
    }

    public Entity getImpassableEntity(int x, int y)
    {
        Entity entity = null;
        for(Entity e : Game.stillObjects)
        {
            if (e.getX() == x && e.getY() == y)
            {
                entity = e;
            }
        }
        for(Integer value : Game.getLayeredEntitySet())
        {
            if (Game.LayeredEntity.get(value).peek().getX() == x && Game.LayeredEntity.get(value).peek().getY() == y) {
                entity = Game.LayeredEntity.get(value).peek();
            }
        }

        for (Bomb bomb : Game.getBombList()) {
            if (bomb.getX() == x && bomb.getY() == y)
            {
                entity = bomb;
            }
        }

        return entity;
    }

    public abstract void killBomber();
    public abstract void chooseSprite();
}
