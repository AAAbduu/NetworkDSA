package DS;

import Data.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of data structure known as a Path, which is nothing but a list of nodes.
 *
 * @author Abdu
 * @author Agus
 */

public class Path {

    private final List<Node> nodes;


    /**
     * Constructor of the class
     * @param s Given a node, construct the path as a SingletonList, because we do not want any copies of this, we need it to be unique.
     */
    public Path(final Node s) {
        this(Collections.singletonList(s));
    }


    /**
     * Constructor of the class.
     * @param nodes List containing some nodes.
     */
    public Path(List<Node> nodes) {
        this.nodes = new ArrayList<>(nodes);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public int length() {
        return nodes.size();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Path path = (Path) o;
        return Objects.equals(nodes, path.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes);
    }

    /**
     * Procedure to print the Path.
     */
    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String result = "";
        for (Node n : nodes) {
            result = result + "->" + n.getUser().getId();
        }
        return result;
    }

    /**
     * Function adds all the nodes of a given path to the current path.
     *
     * @param toAdd Path which contains the nodes to be added to the current Path.
     * @return Path including the nodes added from the parameter.
     */
    public Path add(final Path toAdd) {
        nodes.addAll(toAdd.getNodes());
        return this;
    }
}
