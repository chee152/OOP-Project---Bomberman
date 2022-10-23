package uet.oop.bomberman.entities.character.enemy.AI;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.character.bomber.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;

public class subAI extends AI{
    Bomber bomber = Game.getBomber();
    Enemy enemy;

    //Enemy tự di chuyển
    public subAI(Enemy enemy)
    {
        this.enemy = enemy;
    }


    public int calDirection() {
        return findPath();
    }
    //Thuật toán tìm đường cho Enemy
    public int findPath()
    {
        int direction_;
        if ((double) Math.abs(enemy.getX() - bomber.getX()) < (double) Math.abs(enemy.getY() - bomber.getY())) {
            direction_ = horizontalDirection();
            if (direction_ != -1) {
                return direction_;
            }
            return verticalDirection();
        } else if ((double) Math.abs(enemy.getX() - bomber.getX()) > (double) Math.abs(enemy.getY() - bomber.getY())) {
            direction_ = verticalDirection();
            if (direction_ != -1) {
                return direction_;
            }
            return horizontalDirection();
        } else {
            direction_ = random.nextInt(2);
            if (direction_ == 0) direction_ = verticalDirection();
            else direction_ = horizontalDirection();
            return direction_;
        }
    }
    public int verticalDirection()
    {
        if (bomber.getY() < enemy.getY()) return 2; //đi lên
        if (bomber.getY() > enemy.getY()) return 0; // đi xuống
        return -1;
    }
    public int horizontalDirection()
    {
        if (bomber.getX() < enemy.getX()) return 3;//trái
        if (bomber.getX() > enemy.getX()) return 1; //phải
        return -1;
    }
}
