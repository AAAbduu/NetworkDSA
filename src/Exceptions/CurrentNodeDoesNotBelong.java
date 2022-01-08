package Exceptions;

import Data.Node;

public class CurrentNodeDoesNotBelong extends Throwable {
    private final Node current;

    public CurrentNodeDoesNotBelong(Node current) {
        this.current = current;
    }
}
