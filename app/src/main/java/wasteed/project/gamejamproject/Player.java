package wasteed.project.gamejamproject;

import android.graphics.Canvas;

import wasteed.project.gamejamproject.map.Move;
import wasteed.project.gamejamproject.map.Tower;

public class Player implements IsInteractive {
    private Tower thisTower;
    private UnitInterface unitInterface;
    private Type mType;
    private String name;

    public enum Type {
        HERO, DIPLOMAT, FIGHTER, PEOPLE
    }

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

    public Type getmType() {
        return mType;
    }

    public void setmType(Type mType) {
        this.mType = mType;
    }

    public Tower getTower() {
        return thisTower;
    }

    public UnitInterface getUnitInterface() {
        return unitInterface;
    }
}
