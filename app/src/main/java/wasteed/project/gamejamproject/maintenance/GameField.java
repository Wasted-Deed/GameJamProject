package wasteed.project.gamejamproject.maintenance;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.util.ArrayList;

import wasteed.project.gamejamproject.IsInteractive;
import wasteed.project.gamejamproject.Player;
import wasteed.project.gamejamproject.PlayerSolver;
import wasteed.project.gamejamproject.map.Board;
import wasteed.project.gamejamproject.map.Cell;
import wasteed.project.gamejamproject.map.Flag;
import wasteed.project.gamejamproject.map.Move;
import wasteed.project.gamejamproject.map.MoveType;
import wasteed.project.gamejamproject.map.MyPair;

public class GameField implements IsInteractive {
    public static int CURRENT_PROGRESS;
    private PlayerSolver solver;
    private Board map;

    private final int MAP_X;
    private final int MAP_Y;
    private ArrayList<Player> players;
    String message;
    private Player hero;
    private boolean hasFinished;
    private boolean hasEarnedPoints;
    private boolean isComplete;
    private boolean isFirstHeroUpdate; //TODO delete

    public GameField(int MAP_X, int MAP_Y) {
        this.MAP_X = MAP_X;
        this.MAP_Y = MAP_Y;
        players = new ArrayList<>();
        hero = new Player();
        CURRENT_PROGRESS = 0;
        hasFinished = true;
        hasEarnedPoints = false;
        isComplete = false;
        message = "";
        generateBasicSituation();
    }

    private void generateBasicSituation() {
        players.add(hero);
        hero.setmType(Player.Type.HERO);
        Player dip = new Player();
        dip.setmType(Player.Type.DIPLOMAT);
        players.add(dip);
        dip = new Player();
        dip.setmType(Player.Type.FIGHTER);
        players.add(dip);
        map = new Board(new MyPair(MAP_X, MAP_Y), players);
        solver = new PlayerSolver(players, map);
    }

    @Override
    public void draw(Canvas canvas) {
        Matrix m = new Matrix();
        hero.getUnitInterface().draw(canvas);
        int stepY = 400;
        for (int i = 0; i < MAP_X; i++) {
            for (int j = 0; j < MAP_Y; j++) {
                Bitmap cell;
                //if (i % 2 == 0) //{
                m.setTranslate(i * 50 + (j % 2 == 0 ? 25 : 0), stepY + j * 50);
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
        Paint p = new Paint();
        p.setTextSize(40);
        p.setColor(Color.WHITE);
        canvas.drawText(message, 0, ThreadSolver.SCREEN_HEIGHT - 280, p);
    }

    private void solveHero() {
        int x = (int) ThreadSolver.TOUCH.getX();
        int y = (int) ThreadSolver.TOUCH.getY();
        if (!hasEarnedPoints && ThreadSolver.IS_TOUCHING) {
            hasEarnedPoints = hero.getUnitInterface().toGain(x, y);
        }
        x /= 50;
        y = y - 400;
        y /= 50;
        if (ThreadSolver.IS_TOUCHING && !isComplete) {
            Cell[][] cs = map.getCells();
            if (x >= 0 && x < cs.length &&
                    y >= 0 && y < cs[0].length) {
                if (cs[x][y].getTower() == null) {
                    map.makeMove(new Move(x, y, MoveType.Take), hero.getTower());
                } else if (cs[x][y].getTower().getFLAG() != hero.getTower().getFLAG()) {
                    map.makeMove(new Move(x, y, MoveType.Fight), hero.getTower());
                }
                //if (map.getTower())
                isComplete = true;
            }
        } else if (!ThreadSolver.IS_TOUCHING) {
            if (isComplete) {
                hasFinished = true;
                isComplete = false;
            }
        }
    }

    private boolean isBitmapTouched(Bitmap b, Vector2 pos) {
        float x = ThreadSolver.TOUCH.getX();
        float y = ThreadSolver.TOUCH.getY();
        return x >= pos.getX() && x <= pos.getX() + b.getWidth() &&
                y >= pos.getY() && y <= pos.getY() + b.getHeight();
    }

    @Override
    public void update() {

        if (hasFinished) {
            isFirstHeroUpdate = true;
            CURRENT_PROGRESS++;
            for (Player player : players) {
                if (player.getmType() != Player.Type.HERO) {
                    message += solver.makeMove(player);
                    message += "\n";
                }
            }

            if (CURRENT_PROGRESS % 3 == 0) {
                message = "";
            }
            hasFinished = false;
            hasEarnedPoints = false;
        } else {
            if (isFirstHeroUpdate) {
                isFirstHeroUpdate = false;
                hero.getUnitInterface().updateResources();
            }
            solveHero();
        }
    }
}

