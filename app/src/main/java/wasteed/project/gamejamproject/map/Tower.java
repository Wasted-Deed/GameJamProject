package wasteed.project.gamejamproject.map;

import java.util.Random;

public class Tower {
    // координаты башни на поле
    private final int x;
    private final int y;
    // интерфейся для взаимодействия башни с доской
    private final ClientBoard board;
    // территория, принадлежащая башне
    private final Territory territory;
    // владелец башни
    private final Player owner;

    public Tower(BoardConfig config, ClientBoard board, Player owner) {
        this.board = board;
        this.owner = owner;
        Random random = new Random();
        x = random.nextInt(config.getWidth());
        y = random.nextInt(config.getHeight());
        territory = new PlayersTerritory(config, x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // принадлежит ли клетка территории
    public boolean belongs(int x, int y) {
        return territory.belongs(x, y);
    }

    // присоединить клетку
    public void join(int x, int y) {
        check(x, y);
        territory.join(x, y);
    }

    // отсоединить клетку
    public void lose(int x, int y) {
        if (!belongs(x, y)) {
            throw new IllegalArgumentException();
            // TODO: 02.05.2020 CustomException
        }
        territory.lose(x, y);
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

    public Player getOwner() {
        return owner;
    }

    public int getSize() {
        return territory.getSize();
    }
}
