package DS;

import Data.Node;

import java.util.List;

public class Clique extends Path {

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
