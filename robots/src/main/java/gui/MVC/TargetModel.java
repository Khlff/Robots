package gui.MVC;

import java.awt.*;
import java.util.ArrayList;

import static gui.MVC.ModelsConstants.DEFAULT_X_TARGET_POSITION;
import static gui.MVC.ModelsConstants.DEFAULT_Y_TARGET_POSITION;

public class TargetModel extends Entity {
    private ArrayList<Observer> observers;

    public void setTargetPosition(Point p) {
        setXCoordinate(p.x);
        setYCoordinate(p.y);
    }

    public TargetModel() {
        setYCoordinate(DEFAULT_Y_TARGET_POSITION);
        setXCoordinate(DEFAULT_X_TARGET_POSITION);
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
