package Algorithms;

import Data.Node;

import java.util.Comparator;

public class Quicksort {

    //TODO comentar los metodos de esta clase

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

    private static void sort(Node[] list, int lo, int hi, Comparator<Node> c) {
        if ((hi - lo) <= 0) {
            return;
        }

        int splitPoint = split(list, lo, hi, c);
        sort(list, lo, splitPoint - 1, c);
        sort(list, splitPoint + 1, hi, c);
    }

    public static void sort(Node[] list, Comparator<Node> c) {
        if (list.length <= 1) {
            return;
        }
        sort(list, 0, list.length - 1, c);
    }


}