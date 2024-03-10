package tasks.errorhandling;

public class Exceptions extends Exception {

    public Exceptions(String message) {
        super(message);
    }

    public Exceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public static String toMessage(Throwable throwable) {
        StringBuilder message = new StringBuilder();
        message.append(throwable.getClass().getName());
        String throwableMessage = throwable.getMessage();
        if (throwableMessage != null) {
            message.append(": ").append(throwableMessage);
        }
        return message.toString();
    }
}
