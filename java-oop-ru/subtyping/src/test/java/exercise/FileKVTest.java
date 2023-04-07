package exercise;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.nio.file.Path;

// BEGIN
import static org.junit.jupiter.api.Assertions.assertEquals;
// END

class FileKVTest {
    private static Path filepathInit = Paths.get("src/test/resources/file.json").toAbsolutePath().normalize();
    FileKV actual;

    @BeforeEach
    public void beforeEach() {
        actual = new FileKV(filepathInit.toString(), Map.of("key", "1"));
    }

    // BEGIN
    @Test
    void get() {
        assertEquals("1", actual.get("key", ""));
        assertEquals("key doesn't exist", actual.get("key1", "key doesn't exist"));
    }

    @Test
    void set() {
        actual.set("key", "2");
        assertEquals("2", actual.get("key", ""));

        actual.set("key2", "0");
        assertEquals("0", actual.get("key2", ""));
    }

    @Test
    void unset() {
        actual.unset("key");
        assertEquals("key doesn't exist", actual.get("key", "key doesn't exist"));

        actual.unset("key2");
        assertEquals("key doesn't exist", actual.get("key2", "key doesn't exist"));
    }

    @Test
    void toMap() {
        Map<String, String> expected = new HashMap<>(Map.of("key", "1"));
        assertEquals(expected, actual.toMap());

        expected = Map.of();
        actual.unset("key");
        assertEquals(expected, actual.toMap());
    }
    // END
}
