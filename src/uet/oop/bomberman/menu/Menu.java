package uet.oop.bomberman.menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.Game.HEIGHT;
import static uet.oop.bomberman.Game.WIDTH;

public abstract class Menu {
    protected abstract Scene create();

    protected static BackgroundImage createImage(String url) {
        return new BackgroundImage(
                new Image(url),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
//				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));
                new BackgroundSize(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT, true, true, false, true));

    }

    protected void customText(Text text) {
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        Font font = Font.loadFont("file:res/emulogic.ttf", 45);

        text.setEffect(ds);
        text.setFont(font);

        text.setFill(new LinearGradient(0, 0, 1, 1, true,
                CycleMethod.REFLECT,
                new Stop(0.0, Color.RED),
                new Stop(1.0, Color.GOLD)));
        text.setStroke(Color.BLUEVIOLET);
        text.setStrokeWidth(0.5);

        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(5.0);
        Reflection r = new Reflection();
        r.setFraction(0.6f);
        text.setEffect(r);
    }

    protected VBox initVBox() {
        VBox vb = new VBox();
        vb.setAlignment(Pos.BOTTOM_CENTER);
        vb.setPadding(new Insets(20));
        vb.setSpacing(30);
        vb.setBackground(new Background(createImage("file:res/bgr.jpg")));
        return vb;
    }
}
