package Comparators;

import Data.Node;

import java.util.Comparator;

/**
 * Class which implements a Comparator which is used to sort data comparing in this case by the users idd.
 */
public class SortByUSID implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return o1.getUser().getId().compareTo(o2.getUser().getId());
    }
}
