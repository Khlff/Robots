package gui.MVC;


import static gui.MVC.ModelsConstants.DEFAULT_TARGET_SIZE;
import static gui.windows.ConstantsGUI.GAME_WINDOW_HEIGHT;
import static gui.windows.ConstantsGUI.GAME_WINDOW_WIDTH;

public class TargetModel extends GameEntity {
    public TargetModel() {
        generateNewCoordinates();
        setSize(DEFAULT_TARGET_SIZE);
    }

    public void generateNewCoordinates() {
        setXCoordinate((int) (Math.random() * GAME_WINDOW_WIDTH));
        setYCoordinate((int) (Math.random() * GAME_WINDOW_HEIGHT));
    }
}
