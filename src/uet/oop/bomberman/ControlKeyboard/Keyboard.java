package uet.oop.bomberman.ControlKeyboard;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class Keyboard {
    public static boolean up, down, right, left, space, pause;

    public static void setInputKeyEvent(javafx.scene.input.KeyEvent event)
    {
        if (event.getEventType() == KeyEvent.KEY_PRESSED)
        {
            if (event.getCode() == KeyCode.UP ) {
                up = true;
            } else if (event.getCode() == KeyCode.LEFT) {
                left = true;
            } else if (event.getCode() == KeyCode.DOWN) {
                down = true;
            } else if (event.getCode() == KeyCode.RIGHT) {
                right = true;
            }
        } else {
            up = false;
            left = false;
            down= false;
            right = false;
        }


        if (event.getEventType() == KeyEvent.KEY_PRESSED)
        {
            if (event.getCode() == KeyCode.SPACE)
            {
                space = true;
            } else if (event.getCode() == KeyCode.ESCAPE) {
                 pause = true;
            } else
            {
                space = false;
            }
        }
    }
}
