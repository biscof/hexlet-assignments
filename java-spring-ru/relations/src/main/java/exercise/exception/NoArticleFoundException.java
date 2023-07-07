package exercise.exception;

public class NoArticleFoundException extends RuntimeException {
    public NoArticleFoundException(String message) {
        super(message);
    }
}
