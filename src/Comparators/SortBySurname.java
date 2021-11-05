package Comparators;

import Data.User;

import java.util.Comparator;

public class SortBySurname implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o1.getSurnames().compareTo(o2.getSurnames());
        //TODO Comentar estas clases y metodos, añadir todas las clases de las comparaciones a hacer en el proyecto.
    }
}
