package DS;

import Data.User;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    private int V = 0;
    private int E = 0;
    private final HashMap<User, HashSet<User>> friendData;

    //TODO hacer tests.

    public Graph() {
        this.friendData = new HashMap<User, HashSet<User>>();
    }

    /**
     * Function returns number of vertices in the graph.
     *
     * @return V Number of vertices.
     */
    public int getV() {
        return V;
    }

    /**
     * Adds one vertex when a user is added to the network.
     */
    public void incrementV() {
        this.V++;
    }

    /**
     * Subtracts one vertex when a user is removed from the network.
     */
    public void decrementV() {
        this.V--;
    }


    /**
     * Function returns number of edges in the graph.
     *
     * @return E Number of vertices in the graph.
     */
    public int getE() {
        return E;
    }

    /**
     * Funtion returns the HashMap that contains each Users friendships.
     *
     * @return HashMap containing those users linked by friendships.
     */
    public HashMap<User, HashSet<User>> getFriendData() {
        return friendData;
    }

    /**
     * Method is in charge of linking two users as friends by adding them respectively in each ones list of friends.
     *
     * @param eU1 User1 that adds User2.
     * @param eU2 User2 that adds User1.
     */
    public void addConnections(User eU1, User eU2) {
        this.friendData.get(eU1).add(eU2);
        this.friendData.get(eU2).add(eU1);
        this.E++;
    }

    /**
     * This function returns a list of friends of a single user given this user.
     * Interpreted as the function that returns an iterable object containing users friendships.
     *
     * @param eUser User from who we want to obtain the friends list.
     * @return HashSet that includes all the friendships this user has.
     */
    public HashSet<User> getSingleFList(User eUser) {
        HashSet<User> fList = friendData.get(eUser);
        return fList;
    }

    /**
     * Function that allow to know the degree of a given vertex.
     *
     * @param eUser Vertex, user we want to know how many friends it has.
     * @return Degree of the vertex.
     */
    public int vDegree(User eUser) {
        HashSet<User> fList = friendData.get(eUser);
        return fList.size();
    }

}
