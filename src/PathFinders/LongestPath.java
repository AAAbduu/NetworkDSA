package PathFinders;

import DS.Path;
import Data.Node;
import Exceptions.PathNotFoundException;

import java.util.*;

/**
 * Own implementation of the longest path finder, these is the class containing the functions which are needed to perform the operation.
 */
public class LongestPath {

    private final Node source;
    private final Node target;

    /**
     * Function to be called from main program.
     *
     * @param source Source Node.
     * @param target Target Node.
     * @return Longest Path between the given nodes.
     * @throws PathNotFoundException If path is not found between the given nodes.
     */
    public static Path calculate(Node source, Node target) throws PathNotFoundException {
        return new LongestPath(source, target).calculatePath();
    }

    /**
     * Contructor of the class.
     *
     * @param source Source Node.
     * @param target Target Node.
     */
    private LongestPath(Node source, Node target) {
        this.source = source;
        this.target = target;
    }


    /**
     * Function to calculate the path.
     *
     * @return Longest Path between source and target nodes.
     * @throws PathNotFoundException If no Path is found.
     */
    public Path calculatePath() throws PathNotFoundException {
        return getLongestPath(source, Collections.emptySet());

    }


    /**
     * Function that uses backtracking in order to brute force all possible paths between two given nodes but returns only the longest.
     *
     * @param current Current node being explored.
     * @param visited Visited nodes.
     * @return Longest Path between the source and target nodes.
     * @throws PathNotFoundException If path is not found between source and taget nodes.
     */
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
