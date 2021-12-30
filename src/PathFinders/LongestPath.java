package PathFinders;

import DS.Path;
import Data.Node;
import Exceptions.PathNotFoundException;

import java.util.*;

public class LongestPath {

    private final Node source;
    private final Node target;


    public static Path calculate(Node source, Node target) throws PathNotFoundException {
        return new LongestPath(source, target).calculatePath();
    }

    private LongestPath(Node source, Node target) {
        this.source = source;
        this.target = target;
    }

    public Path calculatePath() throws PathNotFoundException {
        return getLongestPath(source, Collections.emptySet());

    }

    private Path getLongestPath(Node current, Set<Node> visited) throws PathNotFoundException {
        Path longestPath = null;
        if (current.equals(target)) {
            return new Path(target);
        }

        for (Node n : current.getFriends()) {
            try {
                if (!visited.contains(n)) {
                    Set<Node> noGo = new HashSet<>();
                    noGo.add(current);
                    noGo.addAll(visited);
                    Path currentPath = getLongestPath(n, noGo);
                    if (longestPath == null || currentPath.length() > longestPath.length()) {
                        longestPath = currentPath;
                    }

                }
            } catch (Exception e) {
                //IGNORE some friend could not find a path to target, keep trying other options.
            }
        }
        if (current.getFriends().isEmpty()) {
            throw new PathNotFoundException(current, target);
        }
        if (longestPath.getNodes().contains(current)) {
            return new Path(longestPath.getNodes());
        }

        return new Path(current).add(longestPath);

    }


}
