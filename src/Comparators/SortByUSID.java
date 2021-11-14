package Comparators;

import DS.Node;
import Data.User;

import java.util.Comparator;

public class SortByUSID implements Comparator<Node> {


    @Override
    public int compare(Node o1, Node o2) {
        return o1.getThisUser().getID().compareTo(o2.getThisUser().getID());
    }
}
