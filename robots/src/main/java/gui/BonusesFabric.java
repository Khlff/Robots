package gui;

import gui.MVC.GameEntity;
import gui.MVC.bonuses.DownSizeBonus;
import gui.MVC.bonuses.ScoreBonus;
import gui.MVC.bonuses.TeleportBonus;
import gui.MVC.bonuses.UpSizeBonus;

public class BonusesFabric {
    public GameEntity createBonus(String type) {
        GameEntity bonus = null;

        switch (type) {
            case "HALF-SIZE" -> bonus = new DownSizeBonus();
            case "SCORE" -> bonus = new ScoreBonus();
            case "UPGRADE" -> bonus = new UpSizeBonus();
            case "TELEPORT" -> bonus = new TeleportBonus();
        }
        return bonus;
    }
}
