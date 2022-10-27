package uet.oop.bomberman.entities.character.enemy.AI;

public class AILow extends AI{
    public int calculateMovingDirection() {
        int i;
        i = random.nextInt(4); //random các giá trị từ 0 đến 3
        return i;
    }
}
