package uet.oop.bomberman.entities.character.enemy.AI;

public class AILow extends AI{
    public int calculateMovingDirection() {
        return random.nextInt(600);
    }
}
