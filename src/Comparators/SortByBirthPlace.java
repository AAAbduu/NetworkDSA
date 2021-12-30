package Comparators;

import Data.Node;

import java.util.Comparator;

public class SortByBirthPlace implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return o1.getUser().getBirthplace().compareTo(o2.getUser().getBirthplace());
    }
}
