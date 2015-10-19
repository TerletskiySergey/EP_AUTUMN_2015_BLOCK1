package block1.javaGame;

public class MathUtils {
    private static final int RAND_MAX = Integer.MAX_VALUE;

    private MathUtils() {
    }

    public static int rand() {
        return rand(0, RAND_MAX);
    }

    public static int rand(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException();
        }
        return (min == max) ? min : min + (int) (Math.random() * (max - min + 1));
    }
}
