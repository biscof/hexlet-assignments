package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// BEGIN
public class App {
    public static void main(String[] args) {
        System.out.println(scrabble("", "googles"));
    }
    public static boolean scrabble(String rawStr, String word) {
        if (word.length() > rawStr.length()) {
            return false;
        }

        List<String> rawStrArr = Arrays.asList(rawStr.split(""));
        Collections.sort(rawStrArr);

        List<String> wordArr = Arrays.asList(word.toLowerCase().split(""));
        Collections.sort(wordArr);

        ArrayList<String> newWordArr = new ArrayList<>();

        int i = 0;
        int j = 0;

        while(j < rawStrArr.size()) {
            if (wordArr.get(i).equals(rawStrArr.get(j))) {
                newWordArr.add(wordArr.get(i));
                j++;
                i++;
            } else {
                j++;
            }
        }

        return newWordArr.size() == wordArr.size();
    }
}
//END
