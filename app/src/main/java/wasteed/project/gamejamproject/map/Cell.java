package wasteed.project.gamejamproject.map;

public class Cell {
    private CellState state;
    private Tower owner;
    private int x;
    private int y;

    Cell(CellState state, Tower owner, int x, int y) {
        this.state = state;
        this.owner = owner;
        this.x = x;
        this.y = y;
    }

    CellState getState() {
        return state;
    }

    public Tower getTower() {
        return owner;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public void setOwner(Tower owner) {
        this.owner = owner;
    }
}
