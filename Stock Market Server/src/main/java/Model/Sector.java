package Model;

import java.util.Random;

public enum Sector {

    Finance,Technology,ConsumerServices,Manufacturing;

    private static final Sector[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static Sector getRandomSector()  {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
