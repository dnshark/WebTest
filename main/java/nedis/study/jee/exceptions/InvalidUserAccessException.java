package nedis.study.jee.exceptions;

/**
 * Created by Dmitrij on 16.12.2015.
 */
public class InvalidUserAccessException extends Exception {

    public InvalidUserAccessException(String message) {
        super(message);
    }

    public InvalidUserAccessException(Throwable cause) {
        super(cause);
    }

    public InvalidUserAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
