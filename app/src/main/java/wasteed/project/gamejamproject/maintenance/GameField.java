package wasteed.project.gamejamproject.maintenance;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;

import wasteed.project.gamejamproject.IsInteractive;
import wasteed.project.gamejamproject.Player;
import wasteed.project.gamejamproject.map.Board;
import wasteed.project.gamejamproject.map.Cell;
import wasteed.project.gamejamproject.map.Flag;
import wasteed.project.gamejamproject.map.Pair;

public class GameField implements IsInteractive {
    public static int CURRENT_PROGRESS;

    private Board map;

    private final int MAP_X;
    private final int MAP_Y;

    private ArrayList<Player> players;
    private Player hero;
    private boolean hasFinished;
    private Bitmap heroMoney;
    private Bitmap heroArmy;
    private Bitmap heroPeople;

    public GameField(int MAP_X, int MAP_Y) {
        this.MAP_X = MAP_X;
        this.MAP_Y = MAP_Y;
        players = new ArrayList<>();
        hero = new Player();
        CURRENT_PROGRESS = 0;
        hasFinished = false;
        heroMoney = ResourceLoader.getBitmap(ResourceLoader.Image.MONEY);
        heroArmy = ResourceLoader.getBitmap(ResourceLoader.Image.ARMY);
        heroPeople = ResourceLoader.getBitmap(ResourceLoader.Image.PEOPLE);
        generateBasicSituation();
    }

    private void generateBasicSituation() {
        players.add(hero);
        map = new Board(new Pair(MAP_X, MAP_Y), players, null);
    }

    @Override
    public void draw(Canvas canvas) {
        Matrix m = new Matrix();
        m.setTranslate(0, 0);
        canvas.drawBitmap(heroMoney, m, null);
        m.setTranslate(ThreadSolver.SCREEN_WIDTH / 2 - 100, 0);
        canvas.drawBitmap(heroArmy, m, null);
        m.setTranslate(ThreadSolver.SCREEN_WIDTH - 200, 0);
        canvas.drawBitmap(heroPeople, m, null);
        Cell[][] cells = map.getCells();
        int stepY = 200;
        for (int i = 0; i < MAP_X; i++) {
            for (int j = 0; j < MAP_Y; j++) {
                Bitmap cell;
                //if (i % 2 == 0) //{
                    m.setTranslate(i * 54, stepY + j * 60);
                //} else {
                //  m.setTranslate(i * 54 + 27, stepY + j * 60);
                //}
                if (map.getTower(i, j) == null) {
                    cell = ResourceLoader.getBitmap(ResourceLoader.Image.CELL_GRAY);
                    canvas.drawBitmap(cell, m, null);
                    continue;
                }
                Flag flag = map.getTower(i, j).getFLAG();
                if (flag == Flag.Red) {
                    cell = ResourceLoader.getBitmap(ResourceLoader.Image.CELL_RED);
                } else if (flag == Flag.Blue) {
                    cell = ResourceLoader.getBitmap(ResourceLoader.Image.CELL_BLUE);
                } else {
                    cell = ResourceLoader.getBitmap(ResourceLoader.Image.CELL_GREEN);
                }
                canvas.drawBitmap(cell, m, null);
            }
        }
    }

    private void solveHero() {

    }

    @Override
    public void update() {
        if (hasFinished) {
            CURRENT_PROGRESS++;
            for (Player player : players) {
                if (!player.equals(hero)) {
                    //PlayerSolver.makeMove(player);
                }
            }
            hasFinished = false;
        } else {
            solveHero();
        }
    }
}

