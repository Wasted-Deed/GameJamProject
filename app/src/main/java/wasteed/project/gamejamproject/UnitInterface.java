package wasteed.project.gamejamproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import wasteed.project.gamejamproject.maintenance.ResourceLoader;
import wasteed.project.gamejamproject.maintenance.ThreadSolver;

public class UnitInterface implements IsInteractive {
    private int army;
    private int money;
    private int people;
    private Bitmap unitMoney;
    private Bitmap unitArmy;
    private Bitmap unitPeople;
    private Bitmap buttonAttack;
    private Bitmap buttonAllie;
    private boolean isCellChosen;

    public UnitInterface() {
        this(75, 75, 75);
    }

    public UnitInterface(int army, int money, int people) {
        this.army = army;
        this.money = money;
        this.people = people;
        unitMoney = ResourceLoader.getBitmap(ResourceLoader.Image.MONEY);
        unitArmy = ResourceLoader.getBitmap(ResourceLoader.Image.ARMY);
        unitPeople = ResourceLoader.getBitmap(ResourceLoader.Image.PEOPLE);
        buttonAllie = ResourceLoader.getBitmap(ResourceLoader.Image.BUTTON_ALLIE);
        buttonAttack = ResourceLoader.getBitmap(ResourceLoader.Image.BUTTON_ATTACK);
        isCellChosen = false;
    }

    @Override
    public void draw(Canvas canvas) {
        Matrix m = new Matrix();
        m.setTranslate(0, 0);
        Paint p = new Paint();
        p.setTextSize(70);
        p.setColor(Color.WHITE);
        canvas.drawBitmap(unitMoney, m, null);
        canvas.drawText(String.valueOf(getMoney()), 0, 300, p);
        m.setTranslate(ThreadSolver.SCREEN_WIDTH / 2 - 100, 0);
        canvas.drawBitmap(unitArmy, m, null);
        canvas.drawText(String.valueOf(getArmy()), ThreadSolver.SCREEN_WIDTH / 2 - 100
                , 300, p);
        m.setTranslate(ThreadSolver.SCREEN_WIDTH - 200, 0);
        canvas.drawBitmap(unitPeople, m, null);
        canvas.drawText(String.valueOf(getPeople()), ThreadSolver.SCREEN_WIDTH - 200,
                300, p);
        if (isCellChosen) {
            int height = ThreadSolver.SCREEN_HEIGHT;
            int imgH = buttonAllie.getHeight();
            int imgW = buttonAllie.getWidth();
            m.setTranslate(0, height - imgH);
            canvas.drawBitmap(buttonAllie, m, null);
            m.setTranslate(ThreadSolver.SCREEN_WIDTH - imgW, height - imgH);
            canvas.drawBitmap(buttonAttack, m, null);
        }
    }

    @Override
    public void update() {

    }

    public boolean toGain(int x, int y) {
        if (y <= unitArmy.getHeight()) {
            if (x <= unitMoney.getWidth()) {
                money += 20;
                return true;
            }
            if (x >= ThreadSolver.SCREEN_WIDTH / 2 - unitArmy.getWidth() / 2 &&
                    x <= ThreadSolver.SCREEN_WIDTH / 2 + unitArmy.getWidth() / 2) {
                army += 20;
                return true;
            }
            if (x >= ThreadSolver.SCREEN_WIDTH - unitPeople.getWidth()) {
                people += 20;
                return true;
            }
        }
        return false;
    }

    public int getArmy() {
        return army;
    }

    public int getMoney() {
        return money;
    }

    public int getPeople() {
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

    public Bitmap getUnitMoney() {
        return unitMoney;
    }

    public Bitmap getUnitArmy() {
        return unitArmy;
    }

    public Bitmap getUnitPeople() {
        return unitPeople;
    }

    public void updateResources() {
        money += people / 10;
    }
}
