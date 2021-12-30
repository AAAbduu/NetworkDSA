package Comparators;

import Data.Node;

import java.util.Comparator;

public class SortByUSID implements Comparator<Node> {


    @Override
    public int compare(Node o1, Node o2) {
        return o1.getUser().getId().compareTo(o2.getUser().getId());
    }
}
