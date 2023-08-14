package exercise;

import java.util.Map;

class App {
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
