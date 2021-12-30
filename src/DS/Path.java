package DS;

import Data.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Path {

    private final List<Node> nodes;

    public Path(final Node s) {
        this(Collections.singletonList(s));
    }

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

    public Path add(final Path toAdd) {
        nodes.addAll(toAdd.getNodes());
        return this;
    }
}
