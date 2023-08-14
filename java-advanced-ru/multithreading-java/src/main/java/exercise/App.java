package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN

    public static Map<String, Integer> getMinMax(int[] numbers) {
        MaxThread threadMax = new MaxThread(numbers);
        MinThread threadMin = new MinThread(numbers);

        threadMax.start();
        threadMin.start();

        return Map.of(
                "max", threadMax.getMax(),
                "min", threadMin.getMin()
        );
    }
    // END
}
