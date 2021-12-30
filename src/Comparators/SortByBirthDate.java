package Comparators;

import Data.Node;

import java.util.Comparator;

    public class SortByBirthDate implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.getUser().getBirthDate().split("-")[2].compareTo(o2.getUser().getBirthDate().split("-")[2]);

        }
    }
