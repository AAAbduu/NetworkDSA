package PathFinders;

import DS.Path;
import Data.Node;
import Exceptions.PathNotFoundException;

import java.util.*;

/**
 * Own class implementation of the shortest path finder between two given nodes.
 *
 * @author Agus
 * @author Abdu
 */
public class ShortestPath {

    private final Node source;
    private final Node target;
    private final Map<Node, Node> previous = new HashMap();
    private final Set<Node> visited = new HashSet<>();
    private final Queue<Node> toVisit = new LinkedList<>();

    /**
     * Main function to be called from the main program.
     * @param source Source Node.
     * @param target Target Node.
     * @return Path containing all the nodes.
     * @throws PathNotFoundException If there is no path between the given nodes.
     */
    public static Path calculate(Node source, Node target) throws PathNotFoundException {
        if (!source.equals(target))
            return new ShortestPath(source, target).calculate();
        return new Path(source);
    }

    /**
     * Class constructor.
     * @param source Source Node.
     * @param target Target Node.
     */
    private ShortestPath(Node source, Node target) {
        this.source = source;
        this.target = target;
    }

    /**
     * Function that calculates the path using a simple bfs through the graph.
     * @return Path from source Node to target Node.
     * @throws PathNotFoundException If there is no path between the given nodes.
     */
    private Path calculate() throws PathNotFoundException {
        visited.add(source);
        toVisit.add(source);

        while (!toVisit.isEmpty()) {
            Node current = toVisit.poll();
            Set<Node> friends = current.getFriends();

            Iterator<Node> it = friends.iterator();
            while (it.hasNext()) {
                Node friend = it.next();
                if (friend.equals(target)) {
                    previous.put(friend, current);
                    return constructPath();
                } else if (!visited.contains(friend)) {
                    previous.put(friend, current);
                    toVisit.add(friend);
                    visited.add(friend);
                }
            }
        }
        throw new PathNotFoundException(source, target);
    }

    /**
     * If path is found, it should be reversed.
     *
     * @return Corrected Path between the previously given Nodes.
     */
    private Path constructPath() {
        List<Node> reversePath = new ArrayList();
        Node current = target;
        reversePath.add(current);

        while (previous.containsKey(current)) {
            current = previous.get(current);
            reversePath.add(current);
        }

        Collections.reverse(reversePath);

        return new Path(reversePath);
    }

}
