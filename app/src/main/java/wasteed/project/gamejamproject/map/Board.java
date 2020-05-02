package wasteed.project.gamejamproject.map;

import java.util.ArrayList;
import android.graphics.Canvas;

// Основной класс доски/карты
public class Board implements ServerBoard {
    // карта
    private final Cell[][] cells;
    // башни игроков
    private ArrayList<Tower> towers;
    // конфигурация доски
    private final BoardConfig config;
    // стороннний класс для сражения
    private final Battle battleInterface;
    // индекс текущего игрока
    private int current;

    public Board(BoardConfig config, ArrayList<Player> players, Battle battleInterface) {
        this.config = config;
        this.cells = new Cell[config.getHeight()][config.getWidth()];
        this.battleInterface = battleInterface;
        // инициализация поля пустыми клетками
        fill();
        // иницализация начальной расстановки башен
        setTowers(players);
        current = 0;
    }

    private void setTowers(ArrayList<Player> players) {
        towers = new ArrayList<>(players.size());
        for (Player player : players) {
            Tower tower = new Tower(config, this, player);
            towers.add(tower);
            player.setTower(tower);
            cells[tower.getX()][tower.getY()] = Cell.Taken;
        }
    }

    // проверка принятого метода на валидность
    // note: ход под себя здесь не проверяется, а проверятся на стороне башни игрока
    @Override
    public boolean isValid(Move move) {
        MoveType type = move.getType();
        Cell cellState = cells[move.getX()][move.getY()];
        switch (cellState) {
            case Empty : return type == MoveType.Take;
            case Taken : return type == MoveType.Fight;
            default : return false;
        }
    }

    // забрать клетку
    @Override
    public void takeCell(int x, int y) {
        cells[x][y] = Cell.Taken;
        currentTower().join(x, y);
    }

    // поборотся за клетку
    @Override
    public void fightCell(int x, int y) {
        Player defender = findOwner(x, y);
        battleInterface.fight(currentTower().getOwner(), defender, x, y);
    }

    // найти владельца территории
    private Player findOwner(int x, int y) {
        for (Tower tower : towers) {
            if (tower.belongs(x, y)) {
                return tower.getOwner();
            }
        }
        throw new IllegalArgumentException();
        // TODO: 02.05.2020 CustomException
    }

    // просрать клетку
    @Override
    public void loseCell(int x, int y, int playerNumber) {
        cells[x][y] = Cell.Empty;
        towers.get(playerNumber).lose(x, y);
    }

    // башня текущего игрока
    @Override
    public Tower currentTower() {
        return towers.get(current);
    }

    // сделать ход
    @Override
    public void makeMove(Move move) {
        if (!isValid(move)) {
            throw new IllegalArgumentException();
            // TODO: 02.05.2020 CustomException
        }
        if (move.getType() == MoveType.Fight) {
            fightCell(move.getX(), move.getY());
        } else {
            takeCell(move.getX(), move.getY());
        }
        next();
    }

    // передать ход следующему игроку
    public void next() {
        current = (current + 1) % towers.size();
    }

    // начальная инициализация поля
    private void fill() {
        int n = config.getHeight();
        int m = config.getWidth();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cells[i][j] = Cell.Empty;
            }
        }
    }
}
