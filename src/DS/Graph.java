package DS;

import Data.User;

import java.util.*;

public interface Graph<T> {

    /**
     * Function returns number of vertices in the graph.
     *
     * @return V Number of vertices.
     */
    public int getV();

    /**
     * Adds one vertex when a user is added to the network.
     */
    public void incrementV();

    /**
     * Subtracts one vertex when a user is removed from the network.
     */
    public void decrementV();


    /**
     * Function returns number of edges in the graph.
     *
     * @return E Number of vertices in the graph.
     */
    public int getE();

    /**
     * Funtion returns the HashMap that contains each Nodes linking.
     *
     * @return HashMap containing the information of the nodes and its links.
     */
    public HashMap<T, HashSet<T>> getFriendData();


    /**
     * Method is in charge of linking two Nodes.
     *
     * @param eU1 First node adding node eU2.
     * @param eU2 Second node adding node eU1.
     */
    public void addConnections(T eU1, T eU2);


    /**
     * Function that allow to know the degree of a given vertex.
     *
     * @param eUser IS the vertex from which we want to know the degree.
     * @return Degree of the vertex.
     */
    public int vDegree(T eUser);

    /**
     * Function that finds the shortest path between two given nodes.
     *
     * @param s Source node from where we begin the search.
     * @param t Target node.
     * @return ArrayList with all the nodes that connect the two given ones in the shortest path.
     */
    public ArrayList<T> shortPath(T s, T t);





}
