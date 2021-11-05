package Comparators;

import Data.User;

import java.util.Comparator;

public class SortByBirthPlace implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o1.getBirthplace().compareTo(o2.getBirthplace());
    }
}
