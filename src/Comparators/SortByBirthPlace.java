package Comparators;

import Data.Node;

import java.util.Comparator;

/**
 * Class which implements a Comparator which is used to sort data comparing in this case by the birthplace of the users.
 */
public class SortByBirthPlace implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return o1.getUser().getBirthplace().compareTo(o2.getUser().getBirthplace());
    }
}
