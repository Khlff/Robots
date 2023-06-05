package gui;

import gui.MVC.GameEntity;
import gui.MVC.bonuses.*;

public class BonusesFabric {
    public GameEntity createBonus(String type) {
        GameEntity bonus = null;

        switch (type) {
            case "HALF-SIZE" -> bonus = new DownSizeBonus();
            case "SCORE" -> bonus = new ScoreBonus();
            case "UPGRADE" -> bonus = new UpSizeBonus();
            case "TELEPORT" -> bonus = new TeleportBonus();
            case "RESET" -> bonus = new ResetBonus();
        }
        return bonus;
    }
}
