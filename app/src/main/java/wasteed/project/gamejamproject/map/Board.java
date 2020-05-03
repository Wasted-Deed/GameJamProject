package wasteed.project.gamejamproject.map;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

import wasteed.project.gamejamproject.IsInteractive;
import wasteed.project.gamejamproject.Player;


// Основной класс доски/карты
public class Board implements ServerBoard, IsInteractive {
    // карта
    private final Cell[][] cells;
    // башни игроков
    private ArrayList<Tower> towers;
    // конфигурация доски
    private final Pair config;
    // стороннний класс для сражения
    private final Battle battleInterface;
    // индекс текущего игрока
    private int current;

    public Board(Pair config, ArrayList<Player> players, Battle battleInterface) {
        this.config = config;
        this.cells = new Cell[config.getX()][config.getY()];
        this.battleInterface = battleInterface;
        // инициализация поля пустыми клетками
        fill();
        // иницализация начальной расстановки башен
        setTowers(players);
        current = 0;
    }

    private void setTowers(ArrayList<Player> players) {
        towers = new ArrayList<>(players.size());
        Random random = new Random();
        int step = config.getX() / players.size();
        int lay = 0;
        for (Player player : players) {
            int x = random.nextInt(step) + lay;
            lay += step;
            int y = random.nextInt(config.getX());
            cells[x][y] = new Cell(CellState.Taken, null, x, y);
            Tower tower = new Tower(this, cells[x][y], player);
            towers.add(tower);
            player.setTower(tower);
        }
    }

    // проверка принятого метода на валидность
    // note: ход под себя здесь не проверяется, а проверятся на стороне башни игрока
    @Override
    public boolean isValid(Move move) {
        MoveType type = move.getType();
        CellState cellState = cells[move.getX()][move.getY()].getState();
        switch (cellState) {
            case Empty:
                return type == MoveType.Take;
            case Taken:
                return type == MoveType.Fight;
            default:
                return false;
        }
    }

    // забрать клетку
    @Override
    public void takeCell(int x, int y) {
        cells[x][y].setState(CellState.Taken);
        cells[x][y].setOwner(currentTower());
        currentTower().join(cells[x][y]);
    }

    // поборотся за клетку
    @Override
    public void fightCell(int x, int y) {
        cells[x][y].setOwner(battleInterface.fight(currentTower().getOwner(), getTower(x, y).getOwner(), x, y).getTower());
    }

    // просрать клетку
    @Override
    public void loseCell(int x, int y, int playerNumber) {
        cells[x][y].setState(CellState.Empty);
        cells[x][y].setOwner(null);
        towers.get(playerNumber).lose(cells[x][y]);
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
        int n = config.getX();
        int m = config.getY();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cells[i][j] = new Cell(CellState.Empty, null, i, j);
            }
        }
    }

    @Override
    public Tower getTower(int x, int y) {
        return cells[x][y].getTower();
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {

    }

    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public Pair getConfig() {
        return config;
    }
}
