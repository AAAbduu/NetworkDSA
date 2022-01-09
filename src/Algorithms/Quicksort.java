package Algorithms;

import Data.Node;

import java.util.Comparator;


/**
 * Own implementation of quicksort in Java.
 *
 * @author Abdu
 * @author Agus
 */
public class Quicksort {

    /**
     * Function which splist the given array and returns the spliting point of the array.
     * @param list Array with the data to be sorted
     * @param lo Index indicating the lower limit of the array.
     * @param hi Index indicating the upper limit of the array.
     * @param c Comparator indicating by which parameter data should be sorted.
     * @return Index for spliting the array
     */
    private static int split(Node[] list, int lo, int hi, Comparator<Node> c) {
        int left = lo + 1;
        int right = hi;
        Node pivot = list[lo];

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
            Node temp = list[left];
            list[left] = list[right];
            list[right] = temp;
            left++;
            right--;
        }
        list[lo] = list[left - 1];
        list[left - 1] = pivot;


        return left - 1;
    }

    /**
     * Procedure to be called when sorting the data.
     *
     * @param list Array which contains the data to be sorted.
     * @param lo   Lower limit of the array.
     * @param hi   Upper limit of the array.
     * @param c    Comparator indicating which is the parameter to be sorted about.
     */
    private static void sort(Node[] list, int lo, int hi, Comparator<Node> c) {
        if ((hi - lo) <= 0) {
            return;
        }

        int splitPoint = split(list, lo, hi, c);
        sort(list, lo, splitPoint - 1, c);
        sort(list, splitPoint + 1, hi, c);
    }

    /**
     * Main function to be called from the main program in order to sort a given array of data and the sorting parameter.
     *
     * @param list Array of data to be sorted.
     * @param c    Comparator indicating the parameter by which the data should be sorted.
     */
    public static void sort(Node[] list, Comparator<Node> c) {
        if (list.length <= 1) {
            return;
        }
        sort(list, 0, list.length - 1, c);
    }


}