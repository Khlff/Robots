package gui.textures;

import java.util.Random;

/**
 * Содержит пути к текстурам таргетов.
 */
public enum TargetTextures {
    TEXTURE1("./robots/src/main/resources/objectTextures/1.png"),
    TEXTURE2("./robots/src/main/resources/objectTextures/2.png"),
    TEXTURE3("./robots/src/main/resources/objectTextures/3.png"),
    TEXTURE4("./robots/src/main/resources/objectTextures/4.png"),
    TEXTURE5("./robots/src/main/resources/objectTextures/5.png"),
    TEXTURE6("./robots/src/main/resources/objectTextures/6.png"),
    TEXTURE7("./robots/src/main/resources/objectTextures/7.png");

    private final String path;

    TargetTextures(String path) {
        this.path = path;
    }

    /**
     * Получить путь обьекта enum`a.
     * @return путь
     */
    public String getPath() {
        return path;
    }

    /**
     * Получить случайное значение индекса у элемента enum`a.
     * @return индекс элемента в enum`е
     */
    public static TargetTextures getRandomTexture() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}

