package Comparators;

import Data.Node;

import java.util.Comparator;
/**
 * Class which implements a Comparator which is used to sort data comparing in this case by the users home place.
 */
public class SortByHome implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return o1.getUser().getHome().compareTo(o2.getUser().getHome());
    }
}
