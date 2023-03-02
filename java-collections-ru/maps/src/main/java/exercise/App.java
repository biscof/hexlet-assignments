package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> wordCount = new HashMap<>();
        String[] words = sentence.split(" ");

        if (sentence.equals("")) {
            return wordCount;
        }

        for (String word: words) {
            if (wordCount.containsKey(word)) {
                wordCount.replace(word, wordCount.get(word) + 1);
            } else {
                wordCount.put(word, 1);
            }
        }
        return wordCount;
    }

    public static String toString(Map<String, Integer> wordMap) {
        if (wordMap.isEmpty()) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder("{");

        for (Map.Entry<String, Integer> entry: wordMap.entrySet()) {
            stringBuilder.append(String.format("\n\s\s%s: %d",
                    entry.getKey(),
                    entry.getValue()));
        }

        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }
}
//END
