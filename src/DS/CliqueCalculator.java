package DS;

import Data.Node;
import Exceptions.PathNotFoundException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CliqueCalculator {

    private final SocialNetwork sN;

    private CliqueCalculator(SocialNetwork sN) {
        this.sN = sN;
    }

    public static Clique calculate(SocialNetwork sN) {
        return new CliqueCalculator(sN).calculateClique();
    }

    public Clique calculateClique() {
        return getAllCliques(null, Collections.emptySet(), Collections.emptySet());

    }

    private Clique getAllCliques(Node current, Set<Node> toGo, Set<Node> noGo) {
        if (current != null && current.getFriends().equals(toGo)) {
            return new Clique(new Path(current));
        }
        Clique tClique = null;
        for (Node n : sN.getNodes().values()) {

            Set<Node> nFriends = n.getFriends();
            Set<Node> visited = new HashSet<>();
            for (Node f : n.getFriends()) {
                try {
                    if (!noGo.contains(n)) {
                        Set<Node> sMatch = new HashSet<>();
                        sMatch.addAll(nFriends);
                        sMatch.remove(f);
                        sMatch.add(n);
                        visited.add(n);
                        Clique currentClique = getAllCliques(f, sMatch, visited);
                    }
                } catch (Exception e) {
                    //IGNORE
                }
            }
        }
        if (current != null) {
            return new Clique(new Path(current).add(tClique));
        }
        return null;
    }

}

