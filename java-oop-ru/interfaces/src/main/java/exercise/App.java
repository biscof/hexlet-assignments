package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public class App {
    public static void main(String[] args) {
        List<Home> apartments = new ArrayList<>(List.of(
                new Flat(41, 3, 10),
                new Cottage(125.5, 2),
                new Flat(80, 10, 2),
                new Cottage(150, 3)
        ));

        List<String> result = App.buildApartmentsList(apartments, 3);
        System.out.println(result);
    }

    public static List<String> buildApartmentsList(List<Home> properties, int numOfPropertiesToShow) {
        return properties.stream()
                .sorted(Comparator.comparing(Home::getArea))
                .limit(numOfPropertiesToShow)
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
// END
