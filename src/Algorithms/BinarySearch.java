package Algorithms;

import Data.User;

public class BinarySearch {

    public static User binarySearch(User[] arr, String target, int left, int right, String op) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        if (op.contentEquals("sur")) {
            if (arr[mid].getSurnames().contentEquals(target)) {
                System.out.println("Index found: " + mid);
                return arr[mid];
            }
            if (arr[mid].getSurnames().compareTo(target) < 0) {
                //COGER la mitad de la derecha para cpntinuar la busqueda
                System.out.println("Looking right...");
                System.out.println("Current index: " + mid);
                return binarySearch(arr, target, mid + 1, right, "sur");
            } else if (arr[mid].getSurnames().compareTo(target) > 0) {
                System.out.println("Looking left...");
                System.out.println("Current index: " + mid);
                return binarySearch(arr, target, left, mid - 1, "sur");
            }
        } else if (op.contentEquals("bplc")) {
            if (arr[mid].getBirthplace().contentEquals(target)) {
                System.out.println("Index found: " + mid);
                return arr[mid];
            }
            if (arr[mid].getBirthplace().compareTo(target) < 0) {
                //COGER la mitad de la derecha para cpntinuar la busqueda
                System.out.println("Looking right...");
                System.out.println("Current index: " + mid);
                return binarySearch(arr, target, mid + 1, right, "bplc");
            } else if (arr[mid].getBirthplace().compareTo(target) > 0) {
                System.out.println("Looking left...");
                System.out.println("Current index: " + mid);
                return binarySearch(arr, target, left, mid - 1, "bplc");
            }
        }
        return arr[mid];
    }

}
