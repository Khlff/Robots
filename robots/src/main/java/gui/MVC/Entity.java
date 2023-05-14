package gui.MVC;

abstract class Entity {
    protected double xCoordinate;
    protected double yCoordinate;

    protected int size;


    public void setXCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setYCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public double getYCoordinate() {
        return yCoordinate;
    }

    public double getXCoordinate() {
        return xCoordinate;
    }

    public int getSize(){
        return size;
    }

    public void setSize(int size){
        this.size = size;
    }

}
