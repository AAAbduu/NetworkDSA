package Comparators;

import Data.Node;

import java.util.Comparator;

/**
 * Class which implements a Comparator which is used to sort data comparing in this case by the users surnames.
 */
public class SortBySurname implements Comparator<Node> {

    @Override
    public int compare(Node n1, Node n2) {
        return n1.getUser().getSurnames().compareTo(n2.getUser().getSurnames());
    }
}
