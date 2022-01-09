package Comparators;

import Data.Node;
/**
 * Class which implements a Comparator which is used to sort data comparing in this case the birthday of the users.
 */
import java.util.Comparator;

    public class SortByBirthDate implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.getUser().getBirthDate().split("-")[2].compareTo(o2.getUser().getBirthDate().split("-")[2]);

        }
    }
