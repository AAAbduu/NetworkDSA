package DS;

import Algorithms.BinarySearch;
import Algorithms.Quicksort;
import Comparators.SortByBirthPlace;
import Comparators.SortByHome;
import Comparators.SortBySurname;
import Data.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Network {
    private final HashMap<String, User> network;
    private static Network netInstance;
    private final Graph graph;


//TODO Pulir conceptos de hashmap. Pensar como diseñar correctamente el grafo.


    private Network() {
        this.network = new HashMap<String, User>();
        this.graph = new Graph();
    }

    /**
     * Singleton
     *
     * @return Unique instance that can be created.
     */
    public static Network getInstance() {
        if (netInstance == null) {
            netInstance = new Network();
        }
        return netInstance;
    }

    /**
     * Function returns the Users HashMap which holds the keys as the ids and the values as Users.
     *
     * @return HashMap containging ids as keys and Users as values.
     */
    public HashMap<String, User> getNetwork() {
        return network;
    }

    /**
     * Function returns graph of this network.
     *
     * @return Graph.
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Singleton, destroys the unique instance that can be created.
     */
    public static void destroy() {
        netInstance = null;
    }


    /**
     * Method is in charge of linking two users as friends by adding them respectively in each ones list of friends.
     *
     * @param eU1 User1 that adds User2.
     * @param eU2 User2 that adds User1.
     */
    public void addFriend(User eU1, User eU2) {
        if (this.network.containsValue(eU1) && this.network.containsValue(eU2)) {
            graph.addConnections(eU1, eU2);
        }
    }

    /**
     * Procedure that reads a text file with "relationships" between users and adds them as friends.
     */
    public void addFriendsFromFile() { //5th point.
        System.out.print("Write the name of the file: ");
        Scanner doc = new Scanner(System.in);
        String path = doc.nextLine();//read from console the name of the document
        File txtfile = new File(path);
        Scanner usersLinked = null;
        try {
            usersLinked = new Scanner(txtfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        usersLinked.nextLine();//skip the first line of the document because it has no data
        while (usersLinked.hasNext()) {
            String data = usersLinked.nextLine();
            String[] dataLink = data.split(",");
            this.addFriend(this.network.get(dataLink[0]), this.network.get(dataLink[1]));

        }
    }

    /**
     * Procedure that reads all the dataSet in the network and outputs all the information
     * in a txt file named actualDataSet.txt.
     */
    public void outPutInfo() { //4th point.
        int n = 0;
        HashSet<String> studyData = new HashSet<String>();
        HashSet<String> workData = new HashSet<String>();
        HashSet<String> movies = new HashSet<String>();
        try {
            FileWriter writer = new FileWriter("actualDataSet.txt");
            writer.write("idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode\n");
            for (Map.Entry<String, User> entry : this.network.entrySet()) {
                writer.write(entry.getValue().getID() + "," + entry.getValue().getName() + "," + entry.getValue().getSurnames() + ","
                        + entry.getValue().getBirthDate() + "," + entry.getValue().getGender() + "," + entry.getValue().getBirthplace() + ","
                        + entry.getValue().getHome() + ",");
                studyData = entry.getValue().getStudyDat();
                workData = entry.getValue().getWorkDat();
                movies = entry.getValue().getMovies();
                n = studyData.size();
                for (String s : studyData) {
                    if (n == 1) {
                        writer.write(s + ",");
                    } else if (n > 1) {
                        writer.write(s + ";");
                        n--;
                    }

                }
                n = workData.size();
                for (String s : workData) {
                    if (n == 1) {
                        writer.write(s + ",");
                    } else if (n > 1) {
                        writer.write(s + ";");
                        n--;
                    }


                }
                n = movies.size();
                for (String s : movies) {
                    if (n == 1) {
                        writer.write(s + ",");
                    } else if (n > 1) {
                        writer.write(s + ";");
                        n--;
                    }

                }
                writer.write(entry.getValue().getGroupCode());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR");
        }

    }

    /**
     * Method for adding a new user to the network.
     *
     * @param eUser User to add into the network.
     */
    public void addUser(User eUser) { //2nd point.
        this.network.put(eUser.getID(), eUser);
        HashSet<User> friendList = new HashSet<User>();
        graph.getFriendData().put(eUser, friendList);
        graph.incrementV();
    }

    /**
     * Printing friendships adjacency list.
     */
    public void printAdjacencyList() {
        for (Map.Entry<String, User> entry : this.network.entrySet()) {
            User uVal = entry.getValue();
            HashMap<User, HashSet<User>> tUser = this.graph.getFriendData();
            HashSet<User> tUsF = tUser.get(uVal);
            for (User u : tUsF) {
                System.out.println(uVal.getName() + "->" + u.getName());
            }
            System.out.println();
        }
    }

    public int getEdges() {
        return this.graph.getE();
    }                                       //TODO TEMPORAL METHODS TO BE REMOVED.

    public int getNodes() {
        return this.graph.getV();
    }

    /**
     * Procedure prints out the information on the social network.
     */
    public void printMapData() {
        for (String s : this.network.keySet()) {
            String userName = this.network.get(s).getName();
            System.out.println(s + " " + userName);
        }
    }

    /**
     * Procedure that reads from a txt file and is able to load information in the social network.
     */
    public void readDataSet() {
        System.out.print("Write the name of the file: ");
        Scanner doc = new Scanner(System.in);
        String path = doc.nextLine();//read from console the name of the document
        File txtfile = new File(path);
        Scanner users = null;
        try {
            users = new Scanner(txtfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        users.nextLine();//skip the first line of the document because it has no data
        while (users.hasNext()) {
            HashSet<String> studyData = new HashSet<String>();
            HashSet<String> workData = new HashSet<String>();
            HashSet<String> movies = new HashSet<String>();
            String userData = users.nextLine();
            String[] data = userData.split(",");
            String id = data[0];
            User newUser = new User(id);
            String name = data[1];
            String surnames = data[2];
            String birthdate = data[3];
            String gender = data[4];
            String birthplace = data[5];
            String home = data[6];
            String[] studyDat = data[7].split(";");
            for (String s : studyDat) {
                studyData.add(s);

            }
            String[] workDat = data[8].split(";");
            for (String s : workDat) {
                workData.add(s);

            }
            String[] movie = data[9].split(";");
            for (String s : movie) {
                movies.add(s);

            }
            String groupCode = data[10];
            newUser.setName(name);
            newUser.setSurnames(surnames);
            newUser.setBirthDate(birthdate);
            newUser.setGender(gender);
            newUser.setBirthplace(birthplace);
            newUser.setHome(home);
            newUser.setStudyDat(studyData);
            newUser.setWorkDat(workData);
            newUser.setMovies(movies);
            newUser.setGroupCode(groupCode);

            this.addUser(newUser);
        }

    }


    public void find() {
        Scanner sc = new Scanner(System.in);
        String input = null;
        System.out.print("1-Search by surname and retrieve friends\n" +
                "2-Search by city and retrieve people born there\n" +
                "3-Search people born in two given dates: ");
        input = sc.nextLine();
        int x = Integer.parseInt(input);
        System.out.print("Introduce finding term: ");
        String target = sc.nextLine();


        if (x == 1)
            sortBandAprint(new SortBySurname(), target, "sur");

        if (x == 2)
            sortBandAprint(new SortByBirthPlace(), target, "bplc");

        if (x == 3) ;


    }


    public void sortBandAprint(Comparator<User> c, String target, String op) {

        Iterator itr = this.network.values().iterator();
        User[] uArr = new User[this.network.size()];
        for (int i = 0; i < this.network.size(); i++) {
            uArr[i] = (User) itr.next();
        }
        for (User u : uArr) {
            System.out.println(u.toString());
        }
        System.out.println();
        Quicksort.sort(uArr, c);

        User found = BinarySearch.binarySearch(uArr, target, 0, uArr.length - 1, op);
        if (found != null) {
            System.out.println(found.toString());
        }
        for (User u : uArr) {

            System.out.println(u.toString());
        }
    }
}
