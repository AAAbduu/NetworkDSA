package Comparators;

import DS.Node;
import Data.User;
import java.util.Comparator;

public class SortBySurname implements Comparator<Node> {

    @Override
    public int compare(Node n1, Node n2) {
        return n1.getUser().getSurnames().compareTo(n2.getUser().getSurnames());
        //TODO Comentar estas clases y metodos, añadir todas las clases de las comparaciones a hacer en el proyecto.
    }
}
