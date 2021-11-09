package Main;

import Comparators.SortBySurname;
import DS.Network;

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
                    String dataset = input.nextLine();
                    sN.readDataSet(dataset);
                    System.out.println(sN.getNodes());
                    break;
                case 2:
                    sN.addFriendsFromFile();
                    sN.printAdjacencyList();
                    System.out.println(sN.getEdges());
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
            }
        }
    }


}
