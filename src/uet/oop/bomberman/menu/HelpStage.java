package uet.oop.bomberman.menu;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.Game.HEIGHT;
import static uet.oop.bomberman.Game.WIDTH;

public class HelpStage extends Menu {

    public static boolean HELP_BACK = false;

    public HelpStage() {
        create();
    }

    @Override
    public Scene create() {

        VBox vb = initVBoxPause();

        Text backText = new Text("Back");

        String str = " Use arrows on keyboard to move bomberman\n"
                +" Esc : pause";



        TextFlow area = new TextFlow();

        Text info = new Text(str);

        customTextPause(backText);
        customTextPause(info);
        info.setStyle("-fx-font-size:25");
        info.setEffect(null);

        area.getChildren().add(info);
        vb.getChildren().add(area);
        vb.getChildren().add(backText);
        VBox.setMargin(area, new Insets(10, 200, 50, 250));
        vb.setFillWidth(true);

        Scene aboutOptionScene = new Scene(vb, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);

        EventHandler<MouseEvent> playHandle = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                playHandle(event, backText);
            }
        };


        backText.addEventFilter(MouseEvent.MOUSE_ENTERED, playHandle);
        backText.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, playHandle);
        backText.addEventFilter(MouseEvent.MOUSE_PRESSED, playHandle);

        return aboutOptionScene;
    }


    private static void playHandle(MouseEvent event, Text text) {
        if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            text.setStyle("-fx-font-size:60");
        } else if (event.getEventType() == MouseEvent.MOUSE_EXITED_TARGET) {
            text.setStyle("-fx-font-size:45");
        } else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
//			System.out.println("chon");
            HELP_BACK = true;
            text.setStyle("-fx-font-size:45");
        } else {
            text.setStyle("-fx-font-size:45");
        }
    }
}

