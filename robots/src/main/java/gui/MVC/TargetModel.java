package gui.MVC;


import gui.textures.TargetTextures;

import static gui.MVC.ModelsConstants.DEFAULT_TARGET_SIZE;
import static gui.windows.ConstantsGUI.GAME_WINDOW_HEIGHT;
import static gui.windows.ConstantsGUI.GAME_WINDOW_WIDTH;

public class TargetModel extends GameEntity {
    public String texturePath;

    public TargetModel() {
        generateNewCoordinates();
        generateNewTexturePath();
        setSize(DEFAULT_TARGET_SIZE);
    }


    /**
     * Генерирует таргету новые координаты
     */
    public void generateNewCoordinates() {
        setXCoordinate((int) (Math.random() * GAME_WINDOW_WIDTH));
        setYCoordinate((int) (Math.random() * GAME_WINDOW_HEIGHT));
    }

    /**
     * Генерирует таргету путь к новой текстуре
     */
    public void generateNewTexturePath() {
        TargetTextures randomTexture = TargetTextures.getRandomTexture();
        texturePath = randomTexture.getPath();
    }
}
