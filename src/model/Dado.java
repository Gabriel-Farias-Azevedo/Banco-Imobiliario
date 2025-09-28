package model;

import java.util.Random;

class Dado {
    private static Random random = new Random();

    public static int[] rolar() {
        return new int[]{ random.nextInt(6) + 1, random.nextInt(6) + 1 };
    }
}
