package Comparators;

import Data.Node;

import java.util.Comparator;

public class SortByHome implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return o1.getUser().getHome().compareTo(o2.getUser().getHome());
    }
}
