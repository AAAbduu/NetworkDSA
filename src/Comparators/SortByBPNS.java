package Comparators;

import Data.User;

import java.util.Comparator;

public class SortByBPNS implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        if (o1.getName().compareTo(o2.getName()) + o1.getSurnames().compareTo(o2.getSurnames()) + o1.getBirthplace().compareTo(o2.getBirthplace()) == 3) {

            return 1;
        } else if (o1.getName().compareTo(o2.getName()) + o1.getSurnames().compareTo(o2.getSurnames()) + o1.getBirthplace().compareTo(o2.getBirthplace()) == 2) {

            return 0;
        }
        return -1;
    }
}


