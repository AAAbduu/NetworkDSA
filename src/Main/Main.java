package Main;

import Comparators.SortBySurname;
import DS.Network;
import DS.Node;
import Data.User;
import Exceptions.UserNotRegisteredException;

import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int state = 0;
        String inputS = null;
        while (state != -1) {
            Network sN = Network.getInstance();
            System.out.println("\n                      MY_MENU");
            System.out.println("1. Load ‘people’ into the network\n2. Load ‘relationships’\n3. Print out people \n4. Search\n5.Write in a txt file actual DataSet.\n6.Residential file.\n7.BFS check\n8.Find those users that like the same movies.");
            Scanner input = new Scanner(System.in);
            System.out.print("Select an option: ");
            inputS = input.nextLine();
            state = Integer.parseInt(inputS);

            switch (state) {
                case 1:
                    System.out.print("Introduce name of the file: ");
                    String dataset = input.nextLine();
                    sN.readDataSet(dataset);
                    break;
                case 2:
                    sN.addFriendsFromFile();
                    sN.printAdjacencyList();
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
                    System.out.print("Introduce the starting user ID please: ");
                    String target = input.nextLine();
                    try {
                        Node n = sN.getNodeByUID(target);
                        if (n != null) {
                            System.out.print(n.getThisUser().getID() + "->");
                            Iterator<Node> miter = sN.getGraph().iteratorBFS(n);
                            while (miter.hasNext()) {
                                System.out.print(miter.next().getThisUser().getID() + "->");
                            }
                        }
                    } catch (UserNotRegisteredException e) {
                        e.printStackTrace();
                    }
                    break;

                case 8:
                    try {
                        sN.sameKprofile();
                    } catch (UserNotRegisteredException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }


}
