package Algorithms;

import Data.User;

import java.util.ArrayList;

public class BinarySearch {

    //TODO hacer tests.

    /**
     * Recursive method which given a target and a sorted array, uses binary search in order to find the information demanded by the user.
     *
     * @param arr    Sorted array.
     * @param target Target to be found.
     * @param left   Left index, at the beginning it should be 0.
     * @param right  Right index, at the beggining it should be the lenght of the arr param -1.
     * @param op     Option chosen to find
     * @return ArrayList containing all the data found given the target.
     */
    public static ArrayList<User> binarySearch(User[] arr, String target, int left, int right, String op) {
        if (left > right) {
            return null;
        }
        ArrayList<User> uArr = new ArrayList<User>();
        int mid = left + (right - left) / 2;
        if (op.contentEquals("sur")) {
            if (arr[mid].getSurnames().contentEquals(target)) {
                int i = mid;
                while (i < arr.length && arr[i].getSurnames().contentEquals(target)) {
                    uArr.add(arr[i]);
                    i++;
                }
                if (mid != 0) {
                    i = mid - 1;
                    while (i >= 0 && arr[i].getSurnames().contentEquals(target)) {
                        uArr.add(arr[i]);
                        i--;
                    }
                }
                return uArr;
            }
            if (arr[mid].getSurnames().compareTo(target) < 0) {
                //COGER la mitad de la derecha para cpntinuar la busqueda
                return binarySearch(arr, target, mid + 1, right, "sur");
            } else if (arr[mid].getSurnames().compareTo(target) > 0) {
                //System.out.println("Looking left...");
                return binarySearch(arr, target, left, mid - 1, "sur");
            }
        } else if (op.contentEquals("bplc")) {
            if (arr[mid].getBirthplace().contentEquals(target)) {
                int i = mid;
                while (arr[i].getBirthplace().contentEquals(target) &&
                        i < arr.length) {
                    uArr.add(arr[i]);
                    i++;
                }
                return uArr;
            }
            if (arr[mid].getBirthplace().compareTo(target) < 0) {
                //COGER la mitad de la derecha para cpntinuar la busqueda
                return binarySearch(arr, target, mid + 1, right, "bplc");
            } else if (arr[mid].getBirthplace().compareTo(target) > 0) {
                //System.out.println("Looking left...");
                return binarySearch(arr, target, left, mid - 1, "bplc");
            }
        }
        return uArr;
    }

    /**
     * Recursive method which given two targets, expected to be dates and a sorted array, uses binary search in order to find the information demanded by the user
     * which is between those given targets.
     *
     * @param arr     Sorted array.
     * @param target  Expected to be a date, the low value of two given dates.
     * @param target2 Expected to be a date, the high value of two given dates.
     * @param left    Left index, at the beginning it should be 0.
     * @param right   Right index, at the beggining it should be the lenght of the arr param -1.
     * @return ArrayList containing all the data found given the target.
     */
    public static ArrayList<User> binarySearchBtI(User[] arr, String target, String target2, int left, int right) {
        if (left > right) {
            return null;
        }
        ArrayList<User> uArr = new ArrayList<User>();
        int mid = left + (right - left) / 2;

        if (Integer.parseInt(arr[mid].getBirthDate().split("-")[2]) >= Integer.parseInt(target)
                && Integer.parseInt(arr[mid].getBirthDate().split("-")[2]) <= Integer.parseInt(target2)) {
            //System.out.println("Index found: " + mid);
            int i = mid;
            while (i < arr.length && Integer.parseInt(target) <= Integer.parseInt(arr[i].getBirthDate().split("-")[2])
                    && Integer.parseInt(arr[i].getBirthDate().split("-")[2]) <= Integer.parseInt(target2)) {
                uArr.add(arr[i]);
                i++;
            }
            if (mid != 0) {
                i = mid - 1;
                while (i >= 0 && Integer.parseInt(arr[i].getBirthDate().split("-")[2]) >= Integer.parseInt(target)
                ) {
                    uArr.add(arr[i]);
                    i--;
                }

                return uArr;
            }
        } else if (Integer.parseInt(arr[mid].getBirthDate().split("-")[2]) < Integer.parseInt(target)) {
            //COGER la mitad de la derecha para cpntinuar la busqueda
            return binarySearchBtI(arr, target, target2, mid + 1, right);
        } else if (Integer.parseInt(arr[mid].getBirthDate().split("-")[2]) > Integer.parseInt(target)) {
            //System.out.println("Looking left...");
            return binarySearchBtI(arr, target, target2, left, mid - 1);
        }
        return null;
    }
}
