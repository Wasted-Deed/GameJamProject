package wasteed.project.gamejamproject;

import android.graphics.Canvas;

public class GameField implements IsInteractive {
    private final int MAP_X;
    private final int MAP_Y;

    public GameField(int MAP_X, int MAP_Y) {
        this.MAP_X = MAP_X;
        this.MAP_Y = MAP_Y;
    }

    @Override
    public void draw(Canvas canvas) {
        
    }

    @Override
    public void update() {

    }
}

