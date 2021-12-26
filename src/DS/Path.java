package DS;

import java.util.Collections;
import java.util.List;

public class Path {

    private final List<Node> nodes;

    public Path(final Node s) {
        this(Collections.singletonList(s));
    }

    public Path(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void print() {
        for (Node n : nodes) {
            System.out.print("->" + n.getUser().getId());
        }
    }

}
