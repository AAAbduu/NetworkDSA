package DS;

import Data.User;

import java.util.*;

public class Graph {
    private int V = 0;
    private int E = 0;
    private HashMap<Node, HashSet<Node>> friendData;

    //TODO hacer tests.

    public Graph() {
        this.friendData = new HashMap<Node, HashSet<Node>>();
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
    public HashMap<Node, HashSet<Node>> getFriendData() {
        return friendData;
    }

    /**
     * Method is in charge of linking two users as friends by adding them respectively in each ones list of friends.
     *
     * @param eU1 User1 that adds User2.
     * @param eU2 User2 that adds User1.
     */
    public void addConnections(Node eU1, Node eU2) {
        eU1.getFriendList().add(eU2);
        eU2.getFriendList().add(eU1);
        this.getFriendData().put(eU1, eU1.getFriendList());
        this.E++;
    }

    /**
     * This function returns a list of friends of a single user given this user.
     * Interpreted as the function that returns an iterable object containing users friendships.
     *
     * @param eUser User from who we want to obtain the friends list.
     * @return HashSet that includes all the friendships this user has.
     */
    public HashSet<Node> getSingleFList(Node eUser) {
        HashSet<Node> fList = friendData.get(eUser);
        return fList;
    }

    public static User retUGN(Node n) {
        return n.getThisUser();
    }


    /**
     * Function that allow to know the degree of a given vertex.
     *
     * @param eUser Vertex, user we want to know how many friends it has.
     * @return Degree of the vertex.
     */
    public int vDegree(Node eUser) {
        HashSet<Node> fList = friendData.get(eUser);
        return fList.size();
    }
//TODO REVISAR ESTE METODO DE BFS

    /**
     * Returns an iterator that performs a BFS starting at given node.
     *
     * @param startUser Starting node to do the BFS.
     * @return an iterator that performs a BFS.
     */
    public Iterator<Node> iteratorBFS(Node startUser) {
        Node n;
        Queue<Node> trQ = new LinkedList<>();
        LinkedList<Node> resultList = new LinkedList<>();
        if (!this.friendData.containsKey(startUser))
            return resultList.iterator();

        boolean[] visited = new boolean[this.V];
        for (int i = 0; i < this.V; i++)
            visited[i] = false;

        trQ.add(startUser);
        visited[startUser.getID()] = true;
        while (!trQ.isEmpty()) {
            n = trQ.poll();
            for (Node d : this.getSingleFList(n)) {
                if (!resultList.contains(d) && !d.equals(startUser))
                    resultList.add(d);
                if (!visited[d.getID()]) {
                    trQ.add(d);
                    visited[d.getID()] = true;
                }
            }
        }
        return resultList.iterator();
    }


}
