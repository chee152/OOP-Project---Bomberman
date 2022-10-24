package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Chuyển động màn hình
 */
public class Camera {
    private float x, y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void tick(Entity entity) {
        x += ((entity.getX() - x) - Game.WIDTH * Sprite.SCALED_SIZE / 2) * 0.5f;
        if (x <= 0) x = 0;
        if (x >= (Game.WIDTH - Game.WIDTH_BUFFER) * Sprite.SCALED_SIZE)
            x = (Game.WIDTH - Game.WIDTH_BUFFER) * Sprite.SCALED_SIZE;

        //Trong game này thì toạ độ camera y không cần thiết
//		y += ((entity.getY() - y) - Game.HEIGHT * Sprite.SCALED_SIZE / 2) * 0.5f;
//		if (y <= 0) y = 0;
//		if (y >= Game.HEIGHT * Sprite.SCALED_SIZE) y = Game.HEIGHT * Sprite.SCALED_SIZE;

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
