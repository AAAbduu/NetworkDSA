package Main;

import DS.Clique;
import DS.Path;
import DS.SocialNetwork;
import Data.Node;
import Data.User;
import Exceptions.CurrentNodeDoesNotBelong;
import Exceptions.PathNotFoundException;
import Exceptions.UserNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {

    public static void main(String[] args) throws UserNotFoundException, PathNotFoundException, CurrentNodeDoesNotBelong {
        int state = -1;
        SocialNetwork sN = new SocialNetwork();
        Scanner input = new Scanner(System.in);
        while (state != 0) {
            state = Integer.parseInt(
                    requestInput("\n                      MY_MENU\n"
                            + "1. Load ‘people’ into the network.\n"
                            + "2. Load ‘relationships’.\n"
                            + "3. Print out people.\n"
                            + "4. Search.\n"
                            + "5. Write in a txt file actual DataSet.\n"
                            + "6. Residential file.\n"
                            + "7. Shortest path between 2 users.\n"
                            + "8. Find those users that like the same movies.\n"
                            + "9. Find largest chain.\n"
                            + "10. Find all Cliques in the graph with more than 4 nodes\n"
                            + "0. Exit\n"
                            + "Select an option: ", input)
            );
            switch (state) {
                case 1:
                    Set<User> users = User.fromFile(requestInput("Introduce name of the file: ", input));
                    for (User user : users) {
                        sN.addUser(user);
                    }
                    break;
                case 2:
                    addFriendsFromFile(sN, requestInput("Write the name of the file: ", input));
                    break;
                case 3:
                    sN.printMapData();
                    break;
                case 4:
                    sN.find();
                    break;
                case 5:
                    sN.outPutInfo();
                    break;
                case 6:
                    sN.residentialFile();
                    break;
                case 7:
                    String s = requestInput("Introduce the starting user ID please: ", input);
                    String t = requestInput("Introduce the ending user ID please: ", input);

                    try {
                        sN.shortestPath(s, t).print();
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    break;
                case 8:
                    try {
                        sN.sameKprofile();
                    } catch (UserNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 9:
                    String start = requestInput("Introduce the starting user ID please: ", input);
                    String target = requestInput("Introduce the ending user ID please: ", input);
                    Path path = sN.longestPath(start, target);
                    path.print();
                    break;
                case 10:
                    List<Clique> listC = sN.getAllCliques();

                    Set<Integer> hcode = new HashSet<>();

                    for (Clique cl : listC) {
                        if (!hcode.contains(cl.hashCode())) {
                            if (cl.getNodes().size() > 4) {
                                cl.print();
                                hcode.add(cl.hashCode());
                            }
                        }
                    }
                    break;
            }
        }
    }

    /**
     * Procedure that reads a text file with "relationships" between users and adds them as friends.
     */
    public static void addFriendsFromFile(SocialNetwork socialNetwork, String path) { //5th point.
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
            try {
                socialNetwork.addConnection(dataLink[0], dataLink[1]);
            } catch (UserNotFoundException e) {
                System.out.println(e);
            }
        }
    }

    private static String requestInput(final String question, final Scanner input) {
        System.out.print(question);
        String answer = input.nextLine();
        return answer;
    }

}
