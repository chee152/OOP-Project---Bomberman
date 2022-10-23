package uet.oop.bomberman.collision;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
public class Collision {
    public static boolean CheckCollision(Entity A, Entity B) {
        double leftB, leftA;
        double rightA,rightB;
        double topA, topB;
        double bottomA, bottomB;

        leftB = B.getX();
        rightB = leftB + Sprite.SCALED_SIZE;
        topB = B.getY();
        bottomB = topB + Sprite.SCALED_SIZE;

        leftA = A.getX() + 1;
        rightA = A.getX() + Sprite.SCALED_SIZE - 1;
        topA = A.getY() + 1;
        bottomA = A.getY() + Sprite.SCALED_SIZE - 1;

        return !((bottomA <= topB) || (topA >= bottomB) || (rightA <= leftB) || (leftA >= rightB));
    }
    public static boolean checkCollisionWithBuffer(Entity A, Entity B) {
        int buff=10;
        double leftB, leftA;
        double rightA,rightB;
        double topA, topB;
        double bottomA, bottomB;


        leftB = B.getX();
        rightB = leftB + Sprite.SCALED_SIZE;
        topB = B.getY();
        bottomB = topB + Sprite.SCALED_SIZE;

        leftA = A.getX() - buff;
        rightA = A.getX() + Sprite.SCALED_SIZE + buff;
        topA = A.getY() - buff;
        bottomA = A.getY() + Sprite.SCALED_SIZE + buff;

        return !((bottomA <= topB) || (topA >= bottomB) || (rightA <= leftB) || (leftA >= rightB));
    }
}
