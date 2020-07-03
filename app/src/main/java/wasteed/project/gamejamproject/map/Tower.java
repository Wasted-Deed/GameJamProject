package wasteed.project.gamejamproject.map;

import java.util.ArrayList;

import wasteed.project.gamejamproject.Player;

public class Tower {
    // координаты башни на поле
    private final int x;
    private final int y;
    // интерфейся для взаимодействия башни с доской
    private final ClientBoard board;
    // территория, принадлежащая башне
    private final ArrayList<Cell> territory;
    // владелец башни
    private final Player owner;
    // flag
    private final Flag FLAG;

    public Tower(ClientBoard board, Cell towerCell, Player owner) {
        this.board = board;
        this.owner = owner;
        x = towerCell.getX();
        y = towerCell.getY();
        FLAG = Flag.randomFlag();
        territory = new ArrayList<>(1);
        territory.add(towerCell);
        towerCell.setOwner(this);
    }

    // принадлежит ли клетка территории
    public boolean belongs(int x, int y) {
        return this.equals(board.getTower(x, y));
    }

    // присоединить клетку
    void join(Cell cell) {
        cell.setState(CellState.Taken);
        cell.setOwner(this);
        territory.add(cell);
    }

    // отсоединить клетку
    void lose(Cell cell) {
        territory.remove(cell);
    }

    // сделать ход с проверкой под себя
    public void makeMove(Move move) {
        check(move.getX(), move.getY());
        board.makeMove(move, this);
    }

    private void check(int x, int y) {
        if (belongs(x, y)) {
            throw new IllegalArgumentException();
            // TODO: 02.05.2020 CustomException
        }
    }

    public boolean isValid(int x, int y) {
        MyPair siz = board.getConfig();
        return x >= 0 && x < siz.getX() && y >= 0 && y < siz.getY();
    }

    public Player getOwner() {
        return owner;
    }

    public int getSize() {
        return territory.size();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Flag getFLAG() {
        return FLAG;
    }

    public ArrayList<Cell> getTerritory() {
        return territory;
    }

}
