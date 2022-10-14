package uet.oop.bomberman.entities.tile.normal;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Tile;

public class Portal extends Tile {
    private boolean pass = false;
    public Portal(int xUnit, int yUnit, Image img){
        super(xUnit, yUnit, img);
    }

}
