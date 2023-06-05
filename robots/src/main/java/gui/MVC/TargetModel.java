package gui.MVC;


import gui.textures.TargetTextures;

import static gui.MVC.ModelsConstants.DEFAULT_TARGET_SIZE;
import static gui.windows.ConstantsGUI.GAME_WINDOW_HEIGHT;
import static gui.windows.ConstantsGUI.GAME_WINDOW_WIDTH;

public class TargetModel extends GameEntity {
    public TargetModel() {
        generateNewCoordinates();
        generateNewTexturePath();
        setSize(DEFAULT_TARGET_SIZE);
        generateNewTexturePath();
    }

    /**
     * Генерирует таргету путь к новой текстуре
     */
    public void generateNewTexturePath() {
        TargetTextures randomTexture = TargetTextures.getRandomTexture();
        setTexturePath(randomTexture.getPath());
    }
}
