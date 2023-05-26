package gui.MVC;

abstract public class BaseEntity {
    protected double xCoordinate;
    protected double yCoordinate;

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public double getYCoordinate() {
        return yCoordinate;
    }

    public double getXCoordinate() {
        return xCoordinate;
    }
}
