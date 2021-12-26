package DS;

import Algorithms.BinarySearch;
import Algorithms.Quicksort;
import Comparators.SortByBSN;
import Comparators.SortByBirthDate;
import Comparators.SortByBirthPlace;
import Comparators.SortByHome;
import Comparators.SortBySurname;
import Data.User;
import Exceptions.PathNotFoundException;
import Exceptions.UserNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


public class SocialNetwork {

    private final Map<String, User> usersbyId;
    private final Map<User, Node> nodes;

    public SocialNetwork() {
        this.usersbyId = new HashMap<>();
        this.nodes = new HashMap<>();
    }

    public void addUser(User user) {
        this.usersbyId.put(user.getId(), user);
        this.nodes.put(user, new Node(user));
    }

    private Node getNode(final String userId) throws UserNotFoundException {
        return getNode(findUser(userId));
    }

    public Node getNode(final User user) throws UserNotFoundException {
        Node node = nodes.get(user);
        if (node == null) {
            throw new UserNotFoundException(user);
        }
        return node;
    }

    public Map<User, Node> getNodes() {
        return nodes;
    }

    public Set<User> getUsers() {
        return nodes.keySet();
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
            for (User user : this.getUsers()) {
                writer.write(user.getId() + "," + user.getName() + "," + user.getSurnames() + ","
                        + user.getBirthDate() + "," + user.getGender() + "," + user.getBirthplace() + ","
                        + user.getHome() + ",");
                studyData = user.getStudyDat();
                workData = user.getWorkDat();
                movies = user.getMovies();
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
                writer.write(user.getGroupCode());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR");
        }

    }

    /**
     * Procedure prints out the information on the social network.
     */
    public void printMapData() {
        for (User user : this.getUsers()) {
            System.out.println(user.getId() + " " + user.getName());
        }
    }

    /**
     * Procedure that is called from the main of the program and its in charge of letting you choose which option to search by
     * and prints the data if found and prints "No data found" if nothing is found.
     */
    public void find() {
        Scanner sc = new Scanner(System.in);
        String input = null;
        System.out.print("1-Search by surname and retrieve friends\n" +
                "2-Search by city and retrieve people born there\n" +
                "3-Search people born in two given dates: ");
        input = sc.nextLine();
        int x = Integer.parseInt(input);
        System.out.println();
        System.out.print("Introduce finding term: ");
        ArrayList<Node> data = null;
        Set<Node> surD;
        if (x == 1) {
            String target = sc.nextLine();
            data = sortBandAprint(new SortBySurname(), target, "sur");
            if (data != null) {
                System.out.println("\nData found by searching conditions: ");
                for (Node e : data) {
                    System.out.println(e.getUser().toString() + "\nFriends: ");
                    surD = e.getFriends();
                    for (Node f : surD) {
                        System.out.println(f.getUser().getId());
                    }
                }
            } else {
                System.out.println("No data found.");
            }
        }

        if (x == 2) {
            String target = sc.nextLine();
            data = sortBandAprint(new SortByBirthPlace(), target, "bplc");
            if (data != null) {
                System.out.println("Found data: ");
                for (Node e : data)
                    System.out.println("User id: " + e.getUser().getId() + " User name: " + e.getUser().getName());
            } else {
                System.out.println("No data was found");
            }
        }
        if (x == 3) {
            String target = sc.nextLine();
            System.out.print("Please introduce the second date: ");
            String target2 = sc.nextLine();
            Node[] uArr = this.nodes.values().toArray(new Node[0]);
            Quicksort.sort(uArr, new SortByBirthDate());


            ArrayList<Node> found = BinarySearch.binarySearchBtI(uArr, target, target2, 0, uArr.length - 1);
            if (found != null) {
                System.out.println("Data found:");
                Node[] fD = found.toArray(new Node[0]);
                Quicksort.sort(fD, new SortByBSN());
                for (Node u : fD) {
                    System.out.println(u.getUser().toString());
                }
            } else {
                System.out.println("No data found.");
            }
        }


    }

    /**
     * Function that uses binary search given a Comparator, which is based on the option chosen by the user,
     * it also receives a target string, which is the value to be found.
     *
     * @param c      Comparator which is needed in order to sort the array depending on the option chosen by the user.
     * @param target Value to be found
     * @param op     Option chosen by the user
     * @return ArrayList <User> which is null if nothing is found and returns the values found if somthihng is found.
     */
    private ArrayList<Node> sortBandAprint(Comparator<Node> c, String target, String op) {
        Node[] uArr = this.nodes.values().toArray(new Node[0]);
        System.out.println("Data not sorted: ");
        for (Node u : uArr) {
            System.out.println(u.getUser().toString());
        }
        System.out.println();
        Quicksort.sort(uArr, c);
        System.out.println();

        System.out.println("\nSorted data by given conditions: ");
        for (Node u : uArr) {
            System.out.println(u.getUser().toString());
        }
        ArrayList<Node> found = BinarySearch.binarySearch(uArr, target, 0, uArr.length - 1, op);
        return found;
    }


    /**
     * Procedure reads a text file and extracts the hometown of the people in the txt file and searches in the dataset thats already in the
     * network who match their birthplaces with the hometown of the people of the file.
     */
    public void residentialFile() {
        Stack<Node> myStck = new Stack<>();
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
            String data = userData;
            String id = data;

            Node n = null;
            try {
                n = this.getNode(id);
                myStck.add(n);
            } catch (UserNotFoundException e) {
                System.out.println("User in residential file is not in the network!");
            }

        }
        Node[] net = this.nodes.values().toArray(new Node[0]);
        Quicksort.sort(net, new SortByHome());
        while (!myStck.empty()) {
            ArrayList<Node> retData = BinarySearch.binarySearch(net, myStck.pop().getUser().getBirthplace(), 0, net.length - 1, "home");
            System.out.println("Data found: ");
            for (Node u : retData) {
                System.out.println("Name: " + u.getUser().getName() + " Surnames: " + u.getUser().getSurnames() +
                        " Current home: " + u.getUser().getHome() + " StudyDat: " + u.getUser().getStudyDat());
                System.out.println();

            }
        }

    }

    /**
     * Method which creates Lists that contains Users that like the same set of movies.
     *
     * @throws UserNotFoundException If User is not found in the network.
     */
    public void sameKprofile() throws UserNotFoundException {
        HashMap<HashSet<String>, ArrayList<Node>> retData = new HashMap<>();
        Node n = null;
        User u = null;
        HashSet<String> m = new HashSet<>();
        Stack<HashSet<String>> s = new Stack<>();
        for (User key : this.nodes.keySet()) {
            ArrayList<Node> nArr = new ArrayList<>();
            n = this.getNode(key);
            nArr.add(n);
            m = n.getUser().getMovies();
            if (!s.contains(m)) {
                for (User key2 : this.nodes.keySet()) {
                    Node n1 = this.getNode(key2);
                    if (!key.equals(key2) && n1.getUser().getMovies().equals(m) && !s.contains(m)) {
                        nArr.add(n1);
                    }
                }
                s.add(m);
                retData.put(m, nArr);
            }
        }

        for (Map.Entry<HashSet<String>, ArrayList<Node>> entry : retData.entrySet()) {
            HashSet<String> key = entry.getKey();
            ArrayList<Node> arr = entry.getValue();

            System.out.print(key.toString() + "->");
            for (Node i : arr) {
                System.out.print(i.getUser().getName() + "->");
            }
            System.out.println();
        }

    }

    //GRAPH METHODS START HERE
    public void addConnection(final String u1, final String u2) throws UserNotFoundException {
        addConnection(findUser(u1), findUser(u2));
    }

    public void addConnection(final User u1, final User u2) throws UserNotFoundException {
        Node n1 = getNode(u1);
        Node n2 = getNode(u2);
        n1.addFriend(n2);
        n2.addFriend(n1);
    }

    // BFS

    public Path shortestPath(final String s, final String t) throws UserNotFoundException, PathNotFoundException {
        return shortestPath(findUser(s), findUser(t));
    }

    private User findUser(final String userId) throws UserNotFoundException {
        User user = usersbyId.get(userId);
        if (user != null) {
            return user;
        }
        throw new UserNotFoundException(user);
    }

    public Path shortestPath(final User s, final User t) throws UserNotFoundException, PathNotFoundException {
        Node source = getNode(s);
        Node target = getNode(t);
        return ShortestPath.calculate(source, target);
    }

}
