package exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReversedSequenceTest {

    @Test
    void lengthTest() {
        int expectedLength = 2;
        CharSequence chars = new ReversedSequence("yl");
        assertEquals(expectedLength, chars.length());

        expectedLength = 0;
        chars = new ReversedSequence("");
        assertEquals(expectedLength, chars.length());
    }

    @Test
    void charAtTest() {
        char expectedChar = 'h';
        CharSequence chars = new ReversedSequence("hp");
        assertEquals(expectedChar, chars.charAt(1));
    }

    @Test
    void subSequenceTest() {
        ReversedSequence expected = new ReversedSequence("llo");
        ReversedSequence actual = new ReversedSequence("hello");
        assertEquals(expected.toString(), actual.subSequence(0, 3).toString());
    }

    @Test
    void testToString() {
        String expected = "joko";
        ReversedSequence actual = new ReversedSequence("okoj");
        assertEquals(expected, actual.toString());
    }
}