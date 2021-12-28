package JTests;

import DS.SocialNetwork;
import DS.Node;
import DS.Path;
import Data.User;
import Exceptions.PathNotFoundException;
import Exceptions.UserNotFoundException;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SocialNetworkTest {

    private SocialNetwork socialNetwork = new SocialNetwork();
    private User u1 = new User("u1");
    private User u2 = new User("u2");
    private User u3 = new User("u3");
    private User u4 = new User("u4");

    @Test
    void addUser() throws UserNotFoundException {
        socialNetwork.addUser(u1);
        assertSame(u1, socialNetwork.getNode(u1).getUser());
    }

    @Test
    void addConnection() throws UserNotFoundException {
        socialNetwork.addUser(u1);
        socialNetwork.addUser(u2);
        socialNetwork.addConnection(u1, u2);
        Set<Node> u1Friends = socialNetwork.getNode(u1).getFriends();
        Set<Node> u2Friends = socialNetwork.getNode(u2).getFriends();

        assertTrue(u1Friends.contains(new Node(u2)));
        assertTrue(u2Friends.contains(new Node(u1)));
    }

    @Test
    void readDataSet() {
        for (User user : User.fromFile("dataset.txt")) {
            socialNetwork.addUser(user);
        }
        assertTrue(!socialNetwork.getNodes().isEmpty());
    }

    @Test
    void outPutInfo() {
        for (User user : User.fromFile("dataset.txt")) {
            socialNetwork.addUser(user);
        }
        socialNetwork.outPutInfo();
        File tempFile = new File("actualDataSet.txt");
        assertTrue(tempFile.exists());
    }

    @Test
    void shortestPath() throws UserNotFoundException, PathNotFoundException {
        socialNetwork.addUser(u1);
        socialNetwork.addUser(u2);
        socialNetwork.addConnection(u1, u2);

        Path path = socialNetwork.shortestPath(u1.getId(), u2.getId());
        String result = path.print();
        System.out.println(result);

        assertTrue(result.contentEquals("->u1->u2"));
    }

    @Test
    void LongestPath() throws UserNotFoundException, PathNotFoundException {
        socialNetwork.addUser(u1);
        socialNetwork.addUser(u2);
        socialNetwork.addUser(u3);
        socialNetwork.addUser(u4);
        socialNetwork.addConnection(u1, u2);
        socialNetwork.addConnection(u2, u3);
        socialNetwork.addConnection(u3, u4);
        socialNetwork.addConnection(u1, u4);
        socialNetwork.addConnection(u1, u3);

        Path path = socialNetwork.longestPath(u2.getId(), u4.getId());
        String result = path.print();
        System.out.println(result);

        assertTrue(result.contentEquals("->u2->u1->u3->u4"));
    }

}
