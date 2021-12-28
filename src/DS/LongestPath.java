package DS;

import Exceptions.PathNotFoundException;

import java.util.*;

public class LongestPath {

    private final Node source;
    private final Node target;
    private final Set<Node> visited;
    private final Stack<Node> dfsPathNoTarget;

    public static Path calculate(Node source, Node target) throws PathNotFoundException {
        if (!source.equals(target))
            return new LongestPath(source, target).calculatePath();

        return new Path(source);
    }

    private LongestPath(Node source, Node target) {
        this.source = source;
        this.target = target;


        visited = new HashSet<>();
        dfsPathNoTarget = new Stack<>();

    }


    public Path calculatePath() throws PathNotFoundException {
        getDfsPathNoTarget(source);
        List<Node> reversedPath = backtrack();
        List<Node> path = new ArrayList<>();
        Iterator<Node> it = reversedPath.iterator();


        while (it.hasNext()) {
            path.add(it.next());
        }

        return new Path(path);
    }


    private void getDfsPathNoTarget(Node current) {
        visited.add(current);
        dfsPathNoTarget.push(current);

        Iterator<Node> it = current.getFriends().iterator();
        while (it.hasNext()) {
            Node n = it.next();
            if (!visited.contains(n) && !n.equals(target) && dfsPathNoTarget.peek().getFriends().contains(n))
                getDfsPathNoTarget(n);
        }
    }

    private List<Node> backtrack() throws PathNotFoundException {
        Node current = dfsPathNoTarget.peek();
        Set<Node> currentF = current.getFriends();
        if (currentF.contains(target)) {
            dfsPathNoTarget.push(target);
            List<Node> path = dfsPathNoTarget.stream().toList();
            return path;

        } else if (!currentF.contains(target) && !dfsPathNoTarget.isEmpty()) {
            dfsPathNoTarget.pop();
            return backtrack();


        }
        throw new PathNotFoundException(source, target);
    }


}
