package Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Node {

    private User user;
    private Set<Node> friends;

    public Node(User user) {
        this.user = user;
        friends = new HashSet<>();
    }

    public User getUser() {
        return user;
    }

    public void addFriend(Node friend) {
        this.friends.add(friend);
    }

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
