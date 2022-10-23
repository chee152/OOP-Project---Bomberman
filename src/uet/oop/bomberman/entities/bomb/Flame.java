package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends MovingEntity {
    private String status;
    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void update()
    {
        animateFlame();
        chooseSprite();
        setImg(sprite.getFxImage());
    }
    public void animateFlame(){
        if (animate < 7500) animate++;
        else animate = 0;
    }

    public void chooseSprite()
    {
        time = Game.TIME_TO_EXPLOSION_BOMB;

        //left, right, top, down last
        switch (status)
        {
            case "left":
                sprite = Sprite.movingSprite(
                        Sprite.explosion_horizontal_left_last,
                        Sprite.explosion_horizontal_left_last1,
                        Sprite.explosion_horizontal_left_last2,
                        animate,time
                );
                break;

            case "right":
                sprite = Sprite.movingSprite(
                        Sprite.explosion_horizontal_right_last,
                        Sprite.explosion_horizontal_right_last1,
                        Sprite.explosion_horizontal_right_last2,
                        animate, time);
                break;

            case "top":
                sprite = Sprite.movingSprite(
                                Sprite.explosion_vertical_top_last,
                                Sprite.explosion_vertical_top_last1,
                                Sprite.explosion_vertical_top_last2,
                                animate, time);

                break;

            case "down":
                sprite = Sprite.movingSprite(
                                Sprite.explosion_vertical_down_last,
                                Sprite.explosion_vertical_down_last1,
                                Sprite.explosion_vertical_down_last2,
                                animate, time);

                break;

            case "horizontal":
                sprite = Sprite.movingSprite(
                                Sprite.explosion_horizontal,
                                Sprite.explosion_horizontal1,
                                Sprite.explosion_horizontal2,
                                animate, time);
                break;

            case "vertical":
                sprite = Sprite.movingSprite(
                                Sprite.explosion_vertical,
                                Sprite.explosion_vertical1,
                                Sprite.explosion_vertical2,
                                animate, time);
                break;
            default:
                sprite = Sprite.movingSprite(
                                Sprite.bomb_exploded,
                                Sprite.bomb_exploded1,
                                Sprite.bomb_exploded2,
                                animate, time);
                break;
        }
    }

    @Override
    protected void afterKill() {

    }
}
