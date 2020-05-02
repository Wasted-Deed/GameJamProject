package wasteed.project.gamejamproject.maintenance;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;

import wasteed.project.gamejamproject.IsInteractive;
import wasteed.project.gamejamproject.Player;

public class GameField implements IsInteractive {
    public static int CURRENT_PROGRESS;

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
    }

    private void generateBasicSituation() {

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

