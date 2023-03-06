package exercise;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class AppTest {
    @Test
    void testApp() {
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };

        String[][] actualImage = App.enlargeArrayImage(image);
        String[][] imageExpected = {
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"}
        };
        assertThat(actualImage).isEqualTo(imageExpected);
    }

    @Test
    void testAppWithEmptyList() {
        String[][] image = {{}};
        String[][] actualImage = App.enlargeArrayImage(image);
        String[][] imageExpected = {{}};
        assertThat(actualImage).isEqualTo(imageExpected);
    }
}
// END
