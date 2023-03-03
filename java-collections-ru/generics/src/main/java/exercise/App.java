package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// BEGIN
public class App {
    public static List<Map<String, String>> findWhere(
            List<Map<String, String>> bookList,
            Map<String, String> where)
    {
        List<Map<String, String>> found = new ArrayList<>();

        for (Map<String, String> item: bookList) {
            int matchCount = 0;

            for (Map.Entry<String, String> bookMap: item.entrySet()) {
                String currentKey = bookMap.getKey();
                if (where.containsKey(currentKey)
                        && where.get(currentKey).equals(bookMap.getValue()))
                {
                    matchCount++;
                }
            }

            if (matchCount == where.size()) {
                found.add(item);
            }
        }

        return found;
    }
}
//END
