package wasteed.project.gamejamproject.map;

import java.util.ArrayList;
import java.util.Random;

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

    public Tower(ClientBoard board, Cell towerCell) {
        this.board = board;
        this.owner = towerCell.getOwner();
        Random random = new Random();
        x = towerCell.getX();
        y = towerCell.getY();
        territory = new ArrayList<>(1);
        territory.add(towerCell);
    }

    // принадлежит ли клетка территории

    public boolean belongs(int x, int y) {
        return false;
    }
    // присоединить клетку

    void join(Cell cell) {

        // TODO: 02.05.2020
    }
    // отсоединить клетку

    void lose(Cell cell) {
        if (!belongs(x, y)) {
            throw new IllegalArgumentException();
            // TODO: 02.05.2020 CustomException
        }
        // TODO: 02.05.2020
    }
    // сделать ход с проверкой под себя

    public void makeMove(Move move) {
        check(move.getX(), move.getY());
        board.makeMove(move);
    }
    private void check(int x, int y) {
        if (belongs(x, y)) {
            throw new IllegalArgumentException();
            // TODO: 02.05.2020 CustomException
        }
    }

    Player getOwner() {
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
}
