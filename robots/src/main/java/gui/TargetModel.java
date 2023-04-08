package gui;

import java.awt.*;

import static gui.ConstantsGUI.DEFAULT_X_TARGET_POSITION;
import static gui.ConstantsGUI.DEFAULT_Y_TARGET_POSITION;

public class TargetModel extends Entity{

    public void setTargetPosition(Point p){
        setXCoordinate(p.x);
        setYCoordinate(p.y);
    }

    public TargetModel(){
        setYCoordinate(DEFAULT_Y_TARGET_POSITION);
        setXCoordinate(DEFAULT_X_TARGET_POSITION);
    }
}
