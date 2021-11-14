package DS;

import Data.User;

import java.util.HashSet;

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


}
