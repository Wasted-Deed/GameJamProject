package wasteed.project.gamejamproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class UnitInterface implements IsInteractive {
    private int army;
    private int money;
    private int people;
    Bitmap armySprite;
    Bitmap moneySprite;
    Bitmap peopleSprite;

    public UnitInterface() {
        this.army = this.money = this.people = 75;
    }

    public UnitInterface(int army, int money, int people) {
        this.army = army;
        this.money = money;
        this.people = people;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {

    }
}
