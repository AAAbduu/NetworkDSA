package DS;

import Data.Node;
import Exceptions.CurrentNodeDoesNotBelong;
import Exceptions.PathNotFoundException;

import java.util.*;

public class CliqueCalculator {

    public static Clique calculateClique(Node node, Set<Node> nodesFriends) throws CurrentNodeDoesNotBelong {
        List<Node> sM = new ArrayList();
        return getAllCliques(node, sM, nodesFriends);

    }

    private static Clique getAllCliques(Node current, List<Node> shouldMatch, Set<Node> nodesCanGo) throws CurrentNodeDoesNotBelong {
        Clique currentClique = null;
        if (nodesCanGo.isEmpty() || current.getFriends().size() < shouldMatch.size()) {
            shouldMatch.add(current);
            return new Clique(new Path(shouldMatch));
        }

        shouldMatch.add(current);


        for (Node n : current.getFriends()) {
            try {

                if (!shouldMatch.contains(n) && n.getFriends().containsAll(shouldMatch) && nodesCanGo.contains(n)) {
                    List<Node> tNsM = new ArrayList<>();
                    tNsM.addAll(shouldMatch);
                    nodesCanGo.remove(n);
                    currentClique = getAllCliques(n, tNsM, nodesCanGo);

                } else {
                    nodesCanGo.remove(n);

                }
            } catch (Exception e) {
                //IGNORE, CURRENT NODE IS DISCARTED.
            }
        }
        if (current.getFriends().containsAll(shouldMatch)) {
            shouldMatch.add(current);
            return new Clique(new Path(shouldMatch));
        }


        if (current.getFriends().isEmpty()) {
            throw new CurrentNodeDoesNotBelong(current);
        }

        return new Clique(new Path(currentClique.getNodes()));
    }

}

