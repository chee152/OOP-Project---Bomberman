package uet.oop.bomberman.ControlKeyboard;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class Keyboard {
    public static boolean up, down, right, left, space, pause;

    public static void setInputKeyEvent(javafx.scene.input.KeyEvent event)
    {
        if (event.getEventType() == KeyEvent.KEY_PRESSED)
        {
            if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
                up = true;
            } else if (event.getCode() == KeyCode.LEFT|| event.getCode() == KeyCode.A) {
                left = true;
            } else if (event.getCode() == KeyCode.DOWN|| event.getCode() == KeyCode.S) {
                down = true;
            } else if (event.getCode() == KeyCode.RIGHT|| event.getCode() == KeyCode.D) {
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
