package gui.MVC;

import gui.windows.GameWindow;

import java.awt.*;
import java.util.ArrayList;


import static gui.windows.ConstantsGUI.GAME_WINDOW_WIDTH;
import static gui.windows.ConstantsGUI.GAME_WINDOW_HEIGHT;
import static gui.MVC.ModelsConstants.DEFAULT_TARGET_SIZE;

public class TargetModel extends Entity {
    private ArrayList<Observer> observers;

    public void setTargetPosition(Point p) {
        setXCoordinate(p.x);
        setYCoordinate(p.y);
    }

    public TargetModel() {
        setXCoordinate(Math.random() * GAME_WINDOW_WIDTH);
        setYCoordinate(Math.random() * GAME_WINDOW_HEIGHT);
        setSize(DEFAULT_TARGET_SIZE);
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void generateNewCoordinates(){
        setXCoordinate(Math.random() * GAME_WINDOW_WIDTH);
        setYCoordinate(Math.random() * GAME_WINDOW_HEIGHT);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
