package uet.oop.bomberman.ControlKeyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    private boolean[] inputKey = new boolean[200];
    public boolean up, down, right, left, space;

    public void update()
    {
        up = inputKey[KeyEvent.VK_UP];
        down = inputKey[KeyEvent.VK_DOWN];
        left = inputKey[KeyEvent.VK_LEFT];
        right = inputKey[KeyEvent.VK_RIGHT];
        space = inputKey[KeyEvent.VK_SPACE];
    }

    public void keyTyped(KeyEvent e)
    {

    }
    public void keyPressed(KeyEvent e)
    {
        inputKey[e.getKeyCode()] = true;
    }
    public void keyReleased(KeyEvent e)
    {
        inputKey[e.getKeyCode()] = false;
    }
}
