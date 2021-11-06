package Algorithms;

import Data.User;

import java.util.ArrayList;

public class BinarySearch {

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
