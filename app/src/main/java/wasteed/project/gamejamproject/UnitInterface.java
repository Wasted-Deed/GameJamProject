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

    protected int getArmy() {
        return army;
    }

    protected int getMoney() {
        return money;
    }

    protected int getPeople() {
        return people;
    }

    protected void setArmy(int army) {
        this.army = army;
    }

    protected void setMoney(int money) {
        this.money = money;
    }

    protected void setPeople(int people) {
        this.people = people;
    }

}
