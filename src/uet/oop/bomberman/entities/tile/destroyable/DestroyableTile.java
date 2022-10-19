package uet.oop.bomberman.entities.tile.destroyable;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Tile;

public class DestroyableTile extends Tile{
    protected int animate = 0;
    protected boolean explode = false;
    protected boolean destroyed = false;
    protected int timeToDisappear = 0;

    public DestroyableTile(int xUnit, int yUnit, Image img)
    {
        super(xUnit, yUnit, img);
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
