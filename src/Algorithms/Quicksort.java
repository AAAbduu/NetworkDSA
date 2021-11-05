package Algorithms;

import Data.User;

import java.util.Comparator;

public class Quicksort {

    private static int split(User[] list, int lo, int hi, Comparator<User> c) {
        int left = lo + 1;
        int right = hi;
        User pivot = list[lo];

        while (true) {
            while (left <= right) {
                if (c.compare(list[left], pivot) < 0) {
                    left++;
                } else {
                    break;
                }
            }

            while (right > left) {
                if (c.compare(list[right], pivot) < 0) {
                    break;
                } else {
                    right--;
                }
            }

            if (left >= right) {
                break;
            }

            // swap left and right items
            User temp = list[left];
            list[left] = list[right];
            list[right] = temp;
            //advance each one step
            left++;
            right--;
        }

        // swap pivot with left-1 position
        list[lo] = list[left - 1];
        list[left - 1] = pivot;
        // return the split point

        return left - 1;
    }

    private static void sort(User[] list, int lo, int hi, Comparator<User> c) {
        if ((hi - lo) <= 0) { // fewer than 2 items
            return;
        }

        int splitPoint = split(list, lo, hi, c);
        sort(list, lo, splitPoint - 1, c);  // left subarray recursion
        sort(list, splitPoint + 1, hi, c);  // right subarray recursion
    }

    public static void sort(User[] list, Comparator<User> c) {
        if (list.length <= 1) {
            return;
        }
        sort(list, 0, list.length - 1, c);
    }


}