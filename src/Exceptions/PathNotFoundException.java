package Exceptions;

import DS.Node;

public class PathNotFoundException extends Exception {
    private final Node source;
    private final Node target;

    public PathNotFoundException(Node source, Node target) {
        this.source = source;
        this.target = target;
    }
}
