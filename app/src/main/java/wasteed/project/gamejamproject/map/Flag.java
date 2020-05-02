package wasteed.project.gamejamproject.map;

import java.util.Random;

public enum Flag {
    Red(false), Green(false), Blue(false), Invalid(false);

    boolean taken;

    private static int amountTaken = 0;

    Flag(boolean taken) {
        this.taken = taken;
    }

    public static Flag[] flags = {Blue, Red, Green};

    public static Flag randomFlag() {
        if (amountTaken == flags.length) {
            return Invalid;
        }
        Random random = new Random();
        Flag flag;
        do {
            flag = flags[random.nextInt(flags.length)];
        } while (flag.taken);
        flag.taken = true;
        amountTaken++;
        return flag;
    }
}
