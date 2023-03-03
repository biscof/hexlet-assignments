package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> result = App.take(numbers1, 1);
        assertThat(result)
                .hasSize(1)
                .contains(1);

        result = App.take(numbers1, 3);
        assertThat(result).isEqualTo(Arrays.asList(1, 2, 3));

        result = App.take(numbers1, 0);
        assertThat(result).isEmpty();

        result = App.take(numbers1, 9);
        assertThat(result)
                .hasSize(4)
                .contains(1, 2, 3, 4);

        List<Integer> numbers2 = new ArrayList<>();
        List<Integer> result2 = App.take(numbers2, 0);
        assertThat(result2).isEmpty();

        result2 = App.take(numbers2, 5);
        assertThat(result2).isEmpty();
        // END
    }
}
