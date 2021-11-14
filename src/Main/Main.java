package Main;

import Comparators.SortBySurname;
import DS.Network;
import DS.Node;
import Data.User;

import java.util.Iterator;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        int state = 0;
        String inputS = null;
        while (state != -1) {
            Network sN = Network.getInstance();
            System.out.println("\n                      MY_MENU");
            System.out.println("1. Load ‘people’ into the network ...\n2. Load ‘relationships’...\n3. Print out people \n4. Search ...\n5.Write in a txt file actual DataSet.");
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
                    /*Iterator<Node> miIter = sN.getGraph().iteratorBFS(new Node(sN.getNetwork().get("Gisella11")));
                    while (miIter.hasNext()) {
                        Node u = miIter.next();
                        System.out.println(u.getThisUser().toString());
                    }*/
                    break;
                case 4:
                    sN.find();
                    break;
                case 5:
                    sN.outPutInfo();
                    break;
            }
        }
    }


}
