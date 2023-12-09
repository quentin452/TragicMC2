package tragicneko.tragicmc.util;

import java.util.Random;

public class NumberHelper {

    public static int getIntWithMinRangeAndVarSign(int min, int var, Random rand) {
        int i = getIntWithMinRange(min, var, rand);
        return rand.nextBoolean() ? -i : i;
    }

    public static int getIntWithMinRange(int min, int var, Random rand) {
        return (min + rand.nextInt(var));
    }

    public static int roundValue(final int value, final int round) {
        final double d = value / round;
        final int i = (int) Math.round(d);
        return i * round;
    }
}
