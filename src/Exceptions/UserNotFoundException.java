package Exceptions;

import Data.User;

public class UserNotFoundException extends Exception {

    private User user;

    public UserNotFoundException(final User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserNotFoundException{" + user.getId() + "}";
    }

}
