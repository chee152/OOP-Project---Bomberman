package uet.oop.bomberman.entities.character.enemy.AI;

public class AILow extends AI{
    public int calculateMovingDirection() {
        int i;
        i = random.nextInt(600);
        return i;
    }
}
