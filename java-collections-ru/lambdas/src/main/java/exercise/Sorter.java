package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;

// BEGIN
public class Sorter {
    public static void main(String[] args) {
        List<Map<String, String>> users = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1990-12-27", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-23", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1989-03-11", "gender", "male"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );

        List<String> men = Sorter.takeOldestMans(users);
        System.out.println(men);
    }

    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(user -> user.get("gender").equals("male"))
                .sorted(Comparator.comparing(user -> user.get("birthday")))
                .map(user -> user.get("name"))
                .toList();
    }
}
// END
