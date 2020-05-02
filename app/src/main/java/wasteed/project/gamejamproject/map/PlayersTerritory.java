package wasteed.project.gamejamproject.map;

public class PlayersTerritory implements Territory {
    private final Cell[][] territory;
    private int size;

    public PlayersTerritory(BoardConfig config, int towerX, int towerY) {
        this.territory = new Cell[config.getHeight()][config.getWidth()];
        join(towerX, towerY);
        size = 1;
    }

    @Override
    public boolean belongs(int x, int y) {
        return territory[x][y] == Cell.Taken;
    }

    @Override
    public void join(int x, int y) {
        territory[x][y] = Cell.Taken;
        size++;
    }

    @Override
    public void lose(int x, int y) {
        territory[x][y] = Cell.Empty;
        size--;
    }

    @Override
    public int getSize() {
        return size;
    }
}
