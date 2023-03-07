package exercise;

import java.util.*;

// BEGIN
public class App {
    public static Map<String, String> genDiff(Map<String, Object> dict1, Map<String, Object> dict2) {
        Map<String, String> comparisons = new LinkedHashMap<>();

        TreeSet<String> newKeySet = new TreeSet<>();

        newKeySet.addAll(dict1.keySet());
        newKeySet.addAll(dict2.keySet());

        for (String key: newKeySet) {
            if (!dict1.containsKey(key)) {
                comparisons.put(key, "added");
            } else if (dict1.containsKey(key)
                    && !dict2.containsKey(key)) {
                comparisons.put(key, "deleted");
            } else if (dict1.containsKey(key)
                    && dict2.containsKey(key)
                    && (dict1.get(key).getClass() != dict2.get(key).getClass()
                    || !dict1.get(key).equals(dict2.get(key)))) {
                comparisons.put(key, "changed");
            } else {
                comparisons.put(key, "unchanged");
            }
        }

        return comparisons;
    }
}
//END
