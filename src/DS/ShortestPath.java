package DS;

import Exceptions.PathNotFoundException;

import java.util.*;

public class ShortestPath {

    private final Node source;
    private final Node target;
    private final Map<Node, Node> previous;
    private final Set<Node> visited;
    private final Queue<Node> toVisit;

    public static Path calculate(Node source, Node target) throws PathNotFoundException {
        return new ShortestPath(source, target).calculate();
    }

    private ShortestPath(Node source, Node target) {
        this.source = source;
        this.target = target;

        previous = new HashMap();
        visited = new HashSet<>();
        toVisit = new LinkedList<>();
    }

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
