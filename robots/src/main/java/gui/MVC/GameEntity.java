package gui.MVC;

import static gui.windows.ConstantsGUI.GAME_WINDOW_HEIGHT;
import static gui.windows.ConstantsGUI.GAME_WINDOW_WIDTH;

public abstract class GameEntity extends BaseEntity {
    private int size;
    private String texturePath;

    public String getTexturePath() {
        return texturePath;
    }

    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void generateNewTexturePath() {
    }

    public void generateNewCoordinates() {
        setXCoordinate((int) (Math.random() * GAME_WINDOW_WIDTH));
        setYCoordinate((int) (Math.random() * GAME_WINDOW_HEIGHT));
    }
}