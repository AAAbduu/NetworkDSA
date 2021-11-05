package Comparators;

import Data.User;

import java.util.Comparator;

public class SortByBirthDate implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o1.getBirthDate().split("-")[2].compareTo(o2.getBirthDate().split("-")[2]);

    }
}
