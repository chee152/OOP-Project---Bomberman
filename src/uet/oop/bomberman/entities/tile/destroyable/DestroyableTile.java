package uet.oop.bomberman.entities.tile.destroyable;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Tile;

public class DestroyableTile extends Tile {
    protected int animate = 0;
    protected boolean exploding = false;
    protected int timeToDisapear = 0;
    protected boolean destroyed = false;

    public boolean isDestroyed() {
        return destroyed;
    }

    public DestroyableTile(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
}
