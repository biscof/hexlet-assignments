package exercise;

import java.util.Comparator;
import java.util.stream.Collectors;

// BEGIN
public class ReversedSequence implements CharSequence {
    String reversed = "";

    public ReversedSequence(String str) {
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed = reversed.concat(str.substring(i, i + 1));
        }
    }

    @Override
    public int length() {
        return reversed.length();
    }

    @Override
    public char charAt(int index) {
        return reversed.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return reversed.substring(start, end);
    }

    @Override
    public String toString() {
        return reversed;
    }
}
// END
