package uet.oop.bomberman.entities.character.enemy.AI;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.character.bomber.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;

public class AIChangeSpeed extends AI{
    Bomber bomber = Game.getBomber();
    Enemy enemy;
    private boolean allowSpeedUp = false;

    public boolean isAllowToSpeedUp() {
        return allowSpeedUp;
    }




    //Enemy tự di chuyển
    public AIChangeSpeed(Enemy enemy)
    {
        this.enemy = enemy;
    }


    public int calculateMovingDirection() {
        return findPath();
    }
    //Thuật toán tìm đường cho Enemy
    public int findPath()
    {
        int direction_;
        double dx = (double) Math.abs(enemy.getX() - bomber.getX());
        double dy = (double) Math.abs(enemy.getY() - bomber.getY());

        if (dx < dy) {
            direction_ = horizontalDirection();
            if (direction_ != -1) {
                return direction_;
            }
            return verticalDirection();
        } else if (dx > dy) {
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
   /* public int verticalDirection()
    {
        if (bomber.getY() < enemy.getY()) return 0; //đi lên
        if (bomber.getY() > enemy.getY()) return 2; // đi xuống
        return -1;
    }
    public int horizontalDirection()
    {
        if (bomber.getX() < enemy.getX()) return 3;//trái
        if (bomber.getX() > enemy.getX()) return 1; //phải
        return -1;
    }
    */
    public int verticalDirection()
    {
        if (bomber.getY() < enemy.getY())
        {
            if (Math.abs(bomber.getY() - enemy.getY()) < 4)
            {
                allowSpeedUp = true;
            }
            else allowSpeedUp = false;
            return 0; //đi lên
        }
        if (bomber.getY() > enemy.getY())
        {
            if (Math.abs(bomber.getY() - enemy.getY()) < 4)
            {
                allowSpeedUp = true;
            }
            else allowSpeedUp = false;
            return 2; // đi xuống
        }
        return -1;
    }
    public int horizontalDirection()
    {
        if (bomber.getX() < enemy.getX())
        {
            if (Math.abs(bomber.getY() - enemy.getY()) < 4) {
                allowSpeedUp = true;
            }
            else allowSpeedUp = false;
            return 3; //trái
        }
        if (bomber.getX() > enemy.getX())
        {
            if (Math.abs(bomber.getY() - enemy.getY()) < 4) {
                allowSpeedUp = true;
            }
            else allowSpeedUp = false;
            return 1; //phải
        }
        return -1;
    }

}
