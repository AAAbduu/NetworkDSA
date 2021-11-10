package DS;

import Algorithms.BinarySearch;
import Algorithms.Quicksort;
import Comparators.*;
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


//TODO hacer tests Junit para cada metodo implementado.


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
    public void readDataSet(String dataset) {
        File txtfile = new File(dataset);
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
            try {
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
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Information wrongly written in input file for this user...: " + data[0]);
                System.out.println(userData);
                continue;
            }


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
        ArrayList<User> data = null;
        HashSet<User> surD;
        if (x == 1) {
            String target = sc.nextLine();
            data = sortBandAprint(new SortBySurname(), target, "sur");
            if (data != null) {
                System.out.println("\nData found by searching conditions: ");
                for (User e : data) {
                    System.out.println(e.toString() + "\nFriends: ");
                    surD = graph.getSingleFList(e);
                    for (User f : surD) {
                        System.out.println(f.getID());
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
                for (User e : data)
                    System.out.println("User id: " + e.getID() + " User name: " + e.getName());
            } else {
                System.out.println("No data was found");
            }
        }
        if (x == 3) {
            String target = sc.nextLine();
            System.out.print("Please introduce the second date: ");
            String target2 = sc.nextLine();
            User[] uArr = this.network.values().toArray(new User[0]);
            Quicksort.sort(uArr, new SortByBirthDate());


            ArrayList<User> found = BinarySearch.binarySearchBtI(uArr, target, target2, 0, uArr.length - 1);
            if (found != null) {
                System.out.println("Data found:");
                //TODO revisar este sort de aqui... implementado pero no estoy seguro...
                User[] fD = found.toArray(new User[0]);
                Quicksort.sort(fD, new SortByBSN());
                for (User u : fD) {
                    System.out.println(u.toString());
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
    private ArrayList<User> sortBandAprint(Comparator<User> c, String target, String op) {
        User[] uArr = this.network.values().toArray(new User[0]);
        System.out.println("Data not sorted: ");
        for (User u : uArr) {
            System.out.println(u.toString());
        }
        System.out.println();
        Quicksort.sort(uArr, c);
        System.out.println();

        System.out.println("\nSorted data by given conditions: ");
        for (User u : uArr) {
            System.out.println(u.toString());
        }
        ArrayList<User> found = BinarySearch.binarySearch(uArr, target, 0, uArr.length - 1, op);
        return found;
    }


    /**
     * Procedure reads a text file and extracts the hometown of the people in the txt file and searches in the dataset thats already in the
     * network who match their birthplaces with the hometown of the people of the file.
     */
    public void residentialFile() {
        Stack<User> myStck = new Stack<User>();
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

            myStck.add(newUser);
        }
        User[] net = this.network.values().toArray(new User[0]);
        Quicksort.sort(net, new SortByBirthPlace());
        while (!myStck.empty()) {
            ArrayList<User> retData = BinarySearch.binarySearch(net, myStck.pop().getHome(), 0, net.length - 1, "bplc");
            System.out.println("Data found: ");
            for (User u : retData) {
                System.out.println("Name: " + u.getName() + " Surnames: " + u.getSurnames() + " Birthplace: " + u.getBirthplace() + " StudyDat: " + u.getStudyDat());
                System.out.println();

            }
        }

    }

}
