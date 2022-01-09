package Algorithms;

import Data.Node;

import java.util.ArrayList;


/**
 * Own binary search implementation in Java.
 *
 * @author Abdu
 * @author Agus
 */
public class BinarySearch {


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
    public static ArrayList<Node> binarySearch(Node[] arr, String target, int left, int right, String op) {
        if (left > right) {
            return null;
        }
        ArrayList<Node> uArr = new ArrayList<>();
        int mid = left + (right - left) / 2;
        if (op.contentEquals("sur")) {
            if (arr[mid].getUser().getSurnames().contentEquals(target)) {
                int i = mid;
                while (i < arr.length && arr[i].getUser().getSurnames().contentEquals(target)) {
                    uArr.add(arr[i]);
                    i++;
                }
                if (mid != 0) {
                    i = mid - 1;
                    while (i >= 0 && arr[i].getUser().getSurnames().contentEquals(target)) {
                        uArr.add(arr[i]);
                        i--;
                    }
                }
                return uArr;
            }
            if (arr[mid].getUser().getSurnames().compareTo(target) < 0) {
                return binarySearch(arr, target, mid + 1, right, "sur");
            } else if (arr[mid].getUser().getSurnames().compareTo(target) > 0) {

                return binarySearch(arr, target, left, mid - 1, "sur");
            }
        } else if (op.contentEquals("bplc")) {
            if (arr[mid].getUser().getBirthplace().contentEquals(target)) {
                int i = mid;
                while (i < arr.length && arr[i].getUser().getBirthplace().contentEquals(target)) {
                    uArr.add(arr[i]);
                    i++;

                }
                if (mid != 0) {
                    i = mid - 1;
                    while (i >= 0 && arr[i].getUser().getBirthplace().contentEquals(target)) {
                        uArr.add(arr[i]);
                        i--;
                    }
                    return uArr;
                }
            }

            if (arr[mid].getUser().getBirthplace().compareTo(target) < 0) {
                return binarySearch(arr, target, mid + 1, right, "bplc");
            } else if (arr[mid].getUser().getBirthplace().compareTo(target) > 0) {
                //System.out.println("Looking left...");
                return binarySearch(arr, target, left, mid - 1, "bplc");
            }
        } else if (op.contentEquals("home")) {
            if (arr[mid].getUser().getHome().contentEquals(target)) {
                int i = mid;
                while (i < arr.length && arr[i].getUser().getHome().contentEquals(target)) {
                    uArr.add(arr[i]);
                    i++;

                }
                if (mid != 0) {
                    i = mid - 1;
                    while (i >= 0 && arr[i].getUser().getHome().contentEquals(target)) {
                        uArr.add(arr[i]);
                        i--;
                    }
                    return uArr;
                }
            }

            if (arr[mid].getUser().getHome().compareTo(target) < 0) {
                return binarySearch(arr, target, mid + 1, right, "home");
            } else if (arr[mid].getUser().getHome().compareTo(target) > 0) {
                return binarySearch(arr, target, left, mid - 1, "home");
            }
        } else if (op.contentEquals("eUID")) {
            if (arr[mid].getUser().getId().contentEquals(target)) {
                uArr.add(arr[mid]);
                return uArr;
            }
            if (arr[mid].getUser().getId().compareTo(target) < 0) {

                return binarySearch(arr, target, mid + 1, right, "eUID");
            } else if (arr[mid].getUser().getId().compareTo(target) > 0) {
                return binarySearch(arr, target, left, mid - 1, "eUID");
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
    public static ArrayList<Node> binarySearchBtI(Node[] arr, String target, String target2, int left, int right) {
        if (left > right) {
            return null;
        }
        ArrayList<Node> uArr = new ArrayList<>();
        int mid = left + (right - left) / 2;

        if (Integer.parseInt(arr[mid].getUser().getBirthDate().split("-")[2]) >= Integer.parseInt(target)
                && Integer.parseInt(arr[mid].getUser().getBirthDate().split("-")[2]) <= Integer.parseInt(target2)) {
            int i = mid;
            while (i < arr.length && Integer.parseInt(target) <= Integer.parseInt(arr[i].getUser().getBirthDate().split("-")[2])
                    && Integer.parseInt(arr[i].getUser().getBirthDate().split("-")[2]) <= Integer.parseInt(target2)) {
                uArr.add(arr[i]);
                i++;
            }
            if (mid != 0) {
                i = mid - 1;
                while (i >= 0 && Integer.parseInt(arr[i].getUser().getBirthDate().split("-")[2]) >= Integer.parseInt(target)
                ) {
                    uArr.add(arr[i]);
                    i--;
                }

                return uArr;
            }
        } else if (Integer.parseInt(arr[mid].getUser().getBirthDate().split("-")[2]) < Integer.parseInt(target)) {
            return binarySearchBtI(arr, target, target2, mid + 1, right);
        } else if (Integer.parseInt(arr[mid].getUser().getBirthDate().split("-")[2]) > Integer.parseInt(target)) {
            return binarySearchBtI(arr, target, target2, left, mid - 1);
        }
        return null;
    }
}
