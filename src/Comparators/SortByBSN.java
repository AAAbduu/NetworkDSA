package Comparators;

import Data.User;

import java.util.Comparator;

public class SortByBSN implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        int result1 = o1.getBirthplace().compareTo(o2.getBirthplace());
        int result2 = o1.getSurnames().compareTo(o2.getSurnames());
        if (result1 == result2) {
            return o1.getName().compareTo(o2.getName());
        }
        return result1;
    }
}
