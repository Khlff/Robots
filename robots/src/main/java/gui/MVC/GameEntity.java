package gui.MVC;

abstract class GameEntity extends BaseEntity {
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

    }
}
