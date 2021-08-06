package chatty.util.exceptions;

public class UserNotFoundException extends ChattyException {
    public UserNotFoundException() {
    }


    public UserNotFoundException(final String userName) {
        super("User with name " + userName + "was not found");
    }


    public UserNotFoundException(final int id, final String username) {
        super("User with name " + username + " and id " + id + " was not found");
    }
}
