package wasteed.project.gamejamproject;

import android.graphics.Canvas;

import java.util.ArrayList;

public class GameField implements IsInteractive {
    private final int MAP_X;
    private final int MAP_Y;

    private ArrayList<Player> players;
    private Player hero;

    public GameField(int MAP_X, int MAP_Y) {
        this.MAP_X = MAP_X;
        this.MAP_Y = MAP_Y;
        players = new ArrayList<>();
        hero = new Player();
    }

    private void generateBasicSituation() {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {

    }
}

