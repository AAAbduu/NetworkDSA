package Comparators;

import Data.Node;

import java.util.Comparator;

/**
 * Class which implements a Comparator which is used to sort data comparing in this case by birthplace, then by name and then by surname of the users.
 */
public class SortByBSN implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        int result1 = o1.getUser().getBirthplace().compareTo(o2.getUser().getBirthplace());
        int result2 = o1.getUser().getSurnames().compareTo(o2.getUser().getSurnames());
        if (result1 == result2) {
            return o1.getUser().getName().compareTo(o2.getUser().getName());
        }
        return result1;
    }
}
