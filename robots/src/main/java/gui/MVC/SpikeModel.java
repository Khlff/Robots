package gui.MVC;

import static gui.windows.ConstantsGUI.GAME_WINDOW_HEIGHT;
import static gui.windows.ConstantsGUI.GAME_WINDOW_WIDTH;

public class SpikeModel extends GameEntity{
    public SpikeModel(){
        generateNewCoordinates();
        setSize((int) (Math.random() * 100) + 100);
        setTexturePath(".\\robots\\src\\main\\resources\\objectTextures\\spike.png");
    }
}
