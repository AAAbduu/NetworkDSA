package Comparators;

import DS.Node;
import Data.User;

import java.util.Comparator;

public class SortByBSN implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        int result1 = o1.getThisUser().getBirthplace().compareTo(o2.getThisUser().getBirthplace());
        int result2 = o1.getThisUser().getSurnames().compareTo(o2.getThisUser().getSurnames());
        if (result1 == result2) {
            return o1.getThisUser().getName().compareTo(o2.getThisUser().getName());
        }
        return result1;
    }
}
