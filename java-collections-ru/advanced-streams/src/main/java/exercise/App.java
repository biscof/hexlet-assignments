package exercise;

import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public class App {
    public static String getForwardedVariables(String file) {
        return Stream.of(file)
                .flatMap(el -> Stream.of(file.split("\n")))
                .filter(str -> str.startsWith("environment"))
                .map(el -> el.replaceAll("environment=", ""))
                .map(el -> el.replaceAll("\"", ""))
                .flatMap(el -> Stream.of(el.split(",")))
                .filter(str -> str.startsWith("X_FORWARDED_"))
                .map(el -> el.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }
}
//END
