package wasteed.project.gamejamproject;

import android.graphics.Canvas;

import wasteed.project.gamejamproject.map.Move;
import wasteed.project.gamejamproject.map.Tower;

public class Player implements IsInteractive {
    private Tower thisTower;
    private UnitInterface unitInterface;

    public Player() {
        unitInterface = new UnitInterface();
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {

    }

    public void setTower(Tower tower) {
        thisTower = tower;
    }

    public void makeMove(Move move) {

    }

    public Tower getTower() {
        return thisTower;
    }

    public UnitInterface getUnitInterface() {
        return unitInterface;
    }
}
