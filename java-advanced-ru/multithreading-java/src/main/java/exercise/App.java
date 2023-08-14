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
        LOGGER.log(Level.INFO, "Thread " + threadMax.getName() + " started");
        threadMin.start();
        LOGGER.log(Level.INFO, "Thread " + threadMin.getName() + " started");

        try {
            threadMax.join();
            LOGGER.log(Level.INFO, "Thread " + threadMax.getName() + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            threadMin.join();
            LOGGER.log(Level.INFO, "Thread " + threadMin.getName() + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Map.of(
                "max", threadMax.getMax(),
                "min", threadMin.getMin()
        );
    }
    // END
}
