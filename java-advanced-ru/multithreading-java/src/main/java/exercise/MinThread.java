package exercise;

// BEGIN
public class MinThread extends Thread {
    int[] numbers;
    int min;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMin() {
        return min;
    }

    @Override
    public void run() {

        if (numbers.length == 0) {
            return;
        }
        min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
    }
}
// END
