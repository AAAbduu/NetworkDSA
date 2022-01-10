package CliqueOperations;

import DS.Clique;
import DS.Path;
import Data.Node;
import Exceptions.CurrentNodeDoesNotBelong;

import java.util.*;

/**
 * Class implementation which contains the needed operations to calculate Cliques through a graph.
 *
 * @author Abdu
 * @author Agus
 */
public class CliqueCalculator {


    /**
     * Function to be called from the main program.
     * @param node Current node to be explored
     * @param nodesFriends Adjacency list of the current node to be explored.
     * @return Clique if found for the current node
     * @throws CurrentNodeDoesNotBelong Exception indicating may be any friend of current node might not belong to the clique that is being constructed.
     */
    public static Clique calculateClique(Node node, Set<Node> nodesFriends) throws CurrentNodeDoesNotBelong {
        List<Node> sM = new ArrayList();
        return getAllCliques(node, sM, nodesFriends);
    }


    /**
     * Function that calculates all possible Cliques existing in a graph.
     * @param current Node explored
     * @param shouldMatch Set which contains the Clique members so far.
     * @param nodesCanGo Set containing the node to be explored.
     * @return Clique containing the path that the algorithm has  gone through in order to find it.
     * @throws CurrentNodeDoesNotBelong Exception indicating may be any friend of current node might not belong to the clique that is being constructed.
     */
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
                //IGNORE, CURRENT NODE IS DISCARDED.
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

