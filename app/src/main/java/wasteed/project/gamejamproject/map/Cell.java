package wasteed.project.gamejamproject.map;

import wasteed.project.gamejamproject.Player;

public class Cell {
    private CellState state;
    private Player owner;
    private int x;
    private int y;

    Cell(CellState state, Player owner, int x, int y) {
        this.state = state;
        this.owner = owner;
        this.x = x;
        this.y = y;
    }

    CellState getState() {
        return state;
    }

    Player getOwner() {
        return owner;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
