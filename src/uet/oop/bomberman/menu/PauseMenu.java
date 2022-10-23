package uet.oop.bomberman.menu;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.Game.HEIGHT;
import static uet.oop.bomberman.Game.WIDTH;

public class PauseMenu extends Menu {
    public static boolean CONTINUE = false;
    public static boolean HELP = false;
    public static boolean RESTART = false;
    public static boolean MAIN_MAIN = false;


    public PauseMenu() {
        create();
    }

    @Override
    public Scene create() {
        VBox vb = initVBox();

        Text continueText = new Text("Continue");
        Text helpText = new Text("Help");
        Text restartText = new Text("Restart");
        Text mainMenu = new Text("Main Menu");

        customText(continueText);
        customText(helpText);
        customText(restartText);
        customText(mainMenu);


        vb.getChildren().add(continueText);
        vb.getChildren().add(restartText);
        vb.getChildren().add(mainMenu);
        vb.getChildren().add(helpText);


        Scene pauseMenuScene = new Scene(vb, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);

        EventHandler<MouseEvent> continueHandle = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                continueHandle(event, continueText);
            }
        };

        EventHandler<MouseEvent> helpHandle = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                helpHandle(event, helpText);
            }
        };

        EventHandler<MouseEvent> restartHandle = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                restartHandle(event, restartText);
            }
        };

        EventHandler<MouseEvent> mainMenuHanlde = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                mainMenuHanlde(event, mainMenu);
            }
        };

        continueText.addEventFilter(MouseEvent.MOUSE_ENTERED, continueHandle);
        continueText.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, continueHandle);
        continueText.addEventFilter(MouseEvent.MOUSE_PRESSED, continueHandle);

        helpText.addEventFilter(MouseEvent.MOUSE_ENTERED, helpHandle);
        helpText.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, helpHandle);
        helpText.addEventFilter(MouseEvent.MOUSE_PRESSED, helpHandle);

        restartText.addEventFilter(MouseEvent.MOUSE_ENTERED, restartHandle);
        restartText.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, restartHandle);
        restartText.addEventFilter(MouseEvent.MOUSE_PRESSED, restartHandle);

        mainMenu.addEventFilter(MouseEvent.MOUSE_ENTERED, mainMenuHanlde);
        mainMenu.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, mainMenuHanlde);
        mainMenu.addEventFilter(MouseEvent.MOUSE_PRESSED, mainMenuHanlde);

        return pauseMenuScene;
    }

    private static void mainMenuHanlde(MouseEvent event, Text text) {
        if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            text.setStyle("-fx-font-size:60");
        } else if (event.getEventType() == MouseEvent.MOUSE_EXITED_TARGET) {
            text.setStyle("-fx-font-size:45");
        } else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
//			System.out.println("chon");
            MAIN_MAIN = true;
            text.setStyle("-fx-font-size:45");
        } else {
            text.setStyle("-fx-font-size:45");
        }
    }


    private static void restartHandle(MouseEvent event, Text text) {
        if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            text.setStyle("-fx-font-size:60");
        } else if (event.getEventType() == MouseEvent.MOUSE_EXITED_TARGET) {
            text.setStyle("-fx-font-size:45");
        } else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            RESTART = true;
//			System.out.println("chon");
            text.setStyle("-fx-font-size:45");
        } else {
            text.setStyle("-fx-font-size:45");
        }
    }

    private static void helpHandle(MouseEvent event, Text text) {
        if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            text.setStyle("-fx-font-size:60");
        } else if (event.getEventType() == MouseEvent.MOUSE_EXITED_TARGET) {
            text.setStyle("-fx-font-size:45");
        } else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
//			System.out.println("chon");
            HELP = true;
            text.setStyle("-fx-font-size:45");
        } else {
            text.setStyle("-fx-font-size:45");
        }
    }

    private static void continueHandle(MouseEvent event, Text text) {
        if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            text.setStyle("-fx-font-size:60");
        } else if (event.getEventType() == MouseEvent.MOUSE_EXITED_TARGET) {
            text.setStyle("-fx-font-size:45");
        } else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
//			System.out.println("chon");
            CONTINUE = true;
            text.setStyle("-fx-font-size:45");
        } else {
            text.setStyle("-fx-font-size:45");
        }
    }


}

