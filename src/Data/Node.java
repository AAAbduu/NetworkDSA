package Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Own node implementation for a graph.
 *
 * @author Agus
 * @author Abdu
 */
public class Node {

    private User user;
    private Set<Node> friends;

    /**
     * Contructor of the class
     * @param user Creating a Node with the given user.
     */
    public Node(User user) {
        this.user = user;
        friends = new HashSet<>();
    }

    public User getUser() {
        return user;
    }

    /**
     * Procedure adds an edge between two nodes.
     * @param friend Node to be linked with.
     */
    public void addFriend(Node friend) {
        this.friends.add(friend);
    }

    /**
     * Function returning the adjacency list of a node.
     * @return
     */
    public Set<Node> getFriends() {
        return friends;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Node node = (Node) o;
        return user.equals(node.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    @Override
    public String toString() {
        return "Node{" +
                "user=" + user +
                '}';
    }
}
