package chatty.util.exceptions;

public class ChattyException extends RuntimeException {

    public ChattyException() {
    }


    public ChattyException(final String message) {
        super(message);
    }
}
