package exercise;

import java.util.Map;

// BEGIN
public class App {
    public static void main(String[] args) {
        KeyValueStorage storage = new InMemoryKV(Map.of("foo", "bar", "bar", "zoo"));
        App.swapKeyValue(storage);
    }
    public static void swapKeyValue(KeyValueStorage dataBase) {
        for (Map.Entry<String, String> entry : dataBase.toMap().entrySet()) {
            dataBase.unset(entry.getKey());
            dataBase.set(entry.getValue(), entry.getKey());
        }
    }
}
// END
