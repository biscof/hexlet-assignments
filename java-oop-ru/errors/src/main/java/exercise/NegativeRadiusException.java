package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {
    String message;

    public NegativeRadiusException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
// END
