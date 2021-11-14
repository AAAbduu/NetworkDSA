package DS;

import Data.User;

import java.util.HashSet;
import java.util.Objects;

public class Node {
    private User thisUser;
    private static int gID = 0;
    private int ID;
    HashSet<Node> relations;


    public Node(User eU) {
        thisUser = eU;
        this.ID = gID;
        gID++;
        relations = new HashSet<>();

    }

    public static int getgID() {
        return gID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public HashSet<Node> getFriendList() {
        return relations;
    }


    public User getThisUser() {
        return thisUser;
    }

    public int getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return getID() == node.getID();
    }


}
