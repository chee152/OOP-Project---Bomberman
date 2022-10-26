package uet.oop.bomberman.entities.character.enemy.AI;

import java.util.Random;

public abstract class AI {
    protected Random random = new Random();

    private boolean allowSpeedUp = false;

    public boolean isAllowToSpeedUp() {
        return allowSpeedUp;
    }
    public abstract int calculateMovingDirection();
}
