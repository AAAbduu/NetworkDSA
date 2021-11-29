package JTests;

import DS.Network;
import DS.Node;
import Data.User;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {

    Network net = Network.getInstance();
    HashMap<String, Node> network = net.getNetwork();
    User u1 = new User("u1");
    User u2 = new User("u2");

    @BeforeEach
    void tearUp() {
        Network net = Network.getInstance();
        HashMap<String, Node> network = net.getNetwork();
    }

    @AfterEach
    void tearDown() {
        Network.destroy();
    }


    @Test
    void addUser() {
        net.addUser(u1);
        assertSame(u1, network.get("u1"));
    }


    @Test
    void addFriend() {
        net.addUser(u1);
        net.addUser(u2);
        net.addFriend(new Node(u1), new Node(u2));
        net.addFriend(new Node(u2), new Node(u1));
        HashSet<Node> eU1 = net.getGraph().getSingleFList(new Node(u1));
        HashSet<Node> eU2 = net.getGraph().getSingleFList(new Node(u2));
        assertTrue(eU1.contains(u2) && eU2.contains(u1));

    }

    @Test
    void readDataSet() {
        net.readDataSet("dataset.txt");
        assertTrue(!net.getNetwork().isEmpty());
    }

    @Test
    void outPutInfo() {
        net.readDataSet("dataset.txt");
        net.outPutInfo();
        File tempFile = new File("actualDataSet.txt");
        assertTrue(tempFile.exists());


    }


}
