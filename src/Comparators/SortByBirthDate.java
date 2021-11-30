package Comparators;

import DS.Node;
import Data.User;

import java.util.Comparator;

    public class SortByBirthDate implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.getThisUser().getBirthDate().split("-")[2].compareTo(o2.getThisUser().getBirthDate().split("-")[2]);

        }
    }
