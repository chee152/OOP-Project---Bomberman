package uet.oop.bomberman.showScore;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import uet.oop.bomberman.graphics.Sprite;

public class Show {
    Text scoreShow;

    public Show() {
        scoreShow = new Text("0");
    }

    public void makeShowScore(Group root, TextFlow textFlow) {

        scoreShow.setFill(new LinearGradient(0, 0, 1, 1, true,
                CycleMethod.REFLECT,
                new Stop(0.0, Color.RED),
                new Stop(1.0, Color.GOLD)));
        scoreShow.setStroke(Color.BLUEVIOLET);
        scoreShow.setStrokeWidth(0.5);
        Font font = Font.loadFont("file:res/emulogic.ttf", 45);
        scoreShow.setTranslateX(Sprite.SCALED_SIZE / 4);
        scoreShow.setTranslateY(Sprite.SCALED_SIZE / 4);

        textFlow.getChildren().add(scoreShow);

        scoreShow.setFont(font);
        root.getChildren().addAll(textFlow);
    }

    public void updateScore(Score score) {
//		score.killEnemy();
        scoreShow.setText(String.valueOf(score.getScore()));
    }


}
