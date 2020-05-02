package wasteed.project.gamejamproject.map;

import java.util.Random;

public enum Flag {
    Red, Green, Blue;

    public static Flag[] flags = {Blue, Red, Green};

    public static Flag randomFlag() {
        Random random = new Random();
        return flags[random.nextInt(flags.length)];
    }
}
