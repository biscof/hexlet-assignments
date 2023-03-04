package exercise;

import java.util.List;

// BEGIN
public class App {
    public static int getCountOfFreeEmails(List<String> list) {
        List<String> freeHosts = List.of(
                "@yandex.ru",
                "@gmail.com",
                "@hotmail.com"
        );

        return list.stream()
                .map(email -> email.substring(email.indexOf("@")))
                .filter(freeHosts::contains)
                .toList()
                .size();
    }
}
// END
