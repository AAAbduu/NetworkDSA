package Comparators;

import DS.Node;
import Data.User;

import java.util.Comparator;

public class SortBySurname implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        return o1.getThisUser().getSurnames().compareTo(o2.getThisUser().getSurnames());
        //TODO Comentar estas clases y metodos, añadir todas las clases de las comparaciones a hacer en el proyecto.
    }
}
