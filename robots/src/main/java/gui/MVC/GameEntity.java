package gui.MVC;

abstract class GameEntity extends BaseEntity {
    protected int size;
    public int getSize(){
        return size;
    }

    public void setSize(int size){
        this.size = size;
    }

}
