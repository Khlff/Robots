package gui.MVC;

import static gui.MVC.ModelsConstants.DEFAULT_SPIKE_SIZE;
import static gui.windows.ConstantsGUI.GAME_WINDOW_HEIGHT;
import static gui.windows.ConstantsGUI.GAME_WINDOW_WIDTH;

public class SpikeModel extends GameEntity{
    public final String texturePath = ".\\robots\\src\\main\\resources\\objectTextures\\spike.png";
    public SpikeModel(){
        generateCoordinates();
        setSize(DEFAULT_SPIKE_SIZE);
    }

    public void generateCoordinates(){
        setXCoordinate((int) (Math.random() * GAME_WINDOW_WIDTH));
        setYCoordinate((int)(Math.random() * GAME_WINDOW_HEIGHT));
    }


}
