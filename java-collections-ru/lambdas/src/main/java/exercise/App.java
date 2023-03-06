package exercise;

import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
public class App {
    public static String[][] enlargeArrayImage(String[][] initArray) {
        if (initArray == null) {
            return null;
        } else if (initArray[0].length == 0) {
            return initArray;
        }

        return Arrays.stream(initArray)
                .flatMap(row -> Stream.of(row, row))
                .map(row -> Arrays.stream(row)
                        .flatMap(el -> Arrays.asList(el, el).stream())
                        .toArray(String[]::new))
                .toArray(String[][]::new);
    }
}
// END
