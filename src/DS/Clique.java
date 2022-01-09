package DS;

import Data.Node;

import java.util.List;

/**
 * Implementation of data structure known as a Clique.
 *
 * @author Agus
 * @author Abdu
 */

public class Clique extends Path {
    /**
     * Contructor of the class which receives a Path data structure, which is nothing but a list of Nodes.
     * @param clique Path containing nodes.
     */
    public Clique(Path clique) {

        super(clique.getNodes());
    }


    @Override
    public int hashCode() {

        List<Node> list = super.getNodes();

        int code = 0;

        for (Node n : list) {
            code = code + n.hashCode();
        }
        return code;
    }
}
