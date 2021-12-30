package Comparators;

import Data.Node;

import java.util.Comparator;

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
