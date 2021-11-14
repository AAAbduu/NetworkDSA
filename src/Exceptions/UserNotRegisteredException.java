package Exceptions;

public class UserNotRegisteredException extends Exception {
    private String message;

    @Override
    public String toString() {
        return "UserNotRegisteredException{" +
                message + '\'' +
                '}';
    }

    public UserNotRegisteredException(String eMessage) {
        super(eMessage);
        this.message = eMessage;
    }


}
