package Exceptions;

public class InvalidUserStringException extends Exception {
    private String message;

    @Override
    public String toString() {
        return "InvalidUserStringException{" +
                message + '\'' +
                '}';
    }

    public InvalidUserStringException(String eMessage) {
        super(eMessage);
        this.message = eMessage;
    }


}
