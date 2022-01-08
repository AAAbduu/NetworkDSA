package JTests;

import DS.Clique;
import DS.SocialNetwork;
import Data.Node;
import DS.Path;
import Data.User;
import Exceptions.CurrentNodeDoesNotBelong;
import Exceptions.PathNotFoundException;
import Exceptions.UserNotFoundException;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SocialNetworkTest {

    private SocialNetwork socialNetwork = new SocialNetwork();
    private User u1 = new User("u1");
    private User u2 = new User("u2");
    private User u3 = new User("u3");
    private User u4 = new User("u4");
    private User u5 = new User("u5");

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
        System.out.println(path);

        assertEquals("->u1->u2", path.toString());
    }

    @Test
    void shortestPath_whenNoPath() {
        socialNetwork.addUser(u1);
        socialNetwork.addUser(u2);

        assertThrows(PathNotFoundException.class, () -> socialNetwork.shortestPath(u1.getId(), u2.getId()));
    }

    @Test
    void shortestPath_sourceAndTargetAreSame() throws UserNotFoundException, PathNotFoundException {
        socialNetwork.addUser(u1);
        socialNetwork.addUser(u2);
        socialNetwork.addConnection(u1, u2);

        Path path = socialNetwork.shortestPath(u1.getId(), u1.getId());
        System.out.println(path);

        assertEquals("->u1", path.toString());
    }

    @Test
    void shortestPath_withLoops() throws UserNotFoundException, PathNotFoundException {
        socialNetwork.addUser(u1);
        socialNetwork.addUser(u2);
        socialNetwork.addConnection(u1, u1);
        socialNetwork.addConnection(u1, u2);
        socialNetwork.addConnection(u1, u2);
        socialNetwork.addConnection(u2, u2);

        Path path = socialNetwork.shortestPath(u1.getId(), u2.getId());
        System.out.println(path);

        assertEquals("->u1->u2", path.toString());
    }

    @Test
    void longestPath() throws UserNotFoundException, PathNotFoundException {
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
        System.out.println(path);

        assertEquals("->u2->u1->u3->u4", path.toString());
    }

    @Test
    void longestPath_simple() throws UserNotFoundException, PathNotFoundException {
        socialNetwork.addUser(u1);
        socialNetwork.addUser(u2);
        socialNetwork.addConnection(u1, u2);

        Path path = socialNetwork.longestPath(u1.getId(), u2.getId());
        System.out.println(path);

        assertEquals("->u1->u2", path.toString());
    }


    @Test
    void longestPath_withLoops() throws UserNotFoundException, PathNotFoundException {
        socialNetwork.addUser(u1);
        socialNetwork.addUser(u2);
        socialNetwork.addUser(u3);
        socialNetwork.addUser(u4);
        socialNetwork.addConnection(u1, u1);
        socialNetwork.addConnection(u4, u4);
        socialNetwork.addConnection(u1, u2);
        socialNetwork.addConnection(u2, u3);
        socialNetwork.addConnection(u3, u4);
        socialNetwork.addConnection(u1, u4);
        socialNetwork.addConnection(u1, u3);

        Path path = socialNetwork.longestPath(u2.getId(), u4.getId());
        System.out.println(path);

        assertEquals("->u2->u1->u3->u4", path.toString());
    }

    @Test
    void longestPath_whenNoPath() {
        socialNetwork.addUser(u1);
        socialNetwork.addUser(u2);

        assertThrows(PathNotFoundException.class, () -> socialNetwork.longestPath(u1.getId(), u2.getId()));
    }

    @Test
    void longestPath_complexExample() throws UserNotFoundException, PathNotFoundException {
        User u5 = new User("u5");
        User u6 = new User("u6");
        User u7 = new User("u7");
        User u8 = new User("u8");
        User u9 = new User("u9");
        User u10 = new User("u10");
        User u11 = new User("u11");

        socialNetwork.addUser(u1);
        socialNetwork.addUser(u2);
        socialNetwork.addUser(u3);
        socialNetwork.addUser(u4);
        socialNetwork.addUser(u5);
        socialNetwork.addUser(u6);
        socialNetwork.addUser(u7);
        socialNetwork.addUser(u8);
        socialNetwork.addUser(u9);
        socialNetwork.addUser(u10);
        socialNetwork.addUser(u11);

        socialNetwork.addConnection(u1, u2);
        socialNetwork.addConnection(u1, u5);
        socialNetwork.addConnection(u1, u8);
        socialNetwork.addConnection(u2, u3);
        socialNetwork.addConnection(u2, u5);
        socialNetwork.addConnection(u3, u4);
        socialNetwork.addConnection(u3, u6);
        socialNetwork.addConnection(u3, u7);
        socialNetwork.addConnection(u4, u7);
        socialNetwork.addConnection(u4, u11);
        socialNetwork.addConnection(u5, u6);
        socialNetwork.addConnection(u5, u8);
        socialNetwork.addConnection(u6, u7);
        socialNetwork.addConnection(u6, u8);
        socialNetwork.addConnection(u6, u9);
        socialNetwork.addConnection(u6, u10);
        socialNetwork.addConnection(u7, u9);
        socialNetwork.addConnection(u7, u11);
        socialNetwork.addConnection(u8, u9);
        socialNetwork.addConnection(u9, u10);
        socialNetwork.addConnection(u10, u11);

        Path path = socialNetwork.longestPath(u1.getId(), u11.getId());
        System.out.println(path);

        assertEquals("->u1->u8->u9->u7->u4->u3->u2->u5->u6->u10->u11", path.toString());
    }

    @Test
    void simpleClique() throws UserNotFoundException, CurrentNodeDoesNotBelong {
        socialNetwork.addUser(u1);
        socialNetwork.addUser(u2);
        socialNetwork.addUser(u3);

        socialNetwork.addConnection(u1, u2);
        socialNetwork.addConnection(u1, u3);
        socialNetwork.addConnection(u2, u3);

        List<Clique> listC = socialNetwork.getAllCliques();

        Set<Integer> hcode = new HashSet<>();

        for (Clique cl : listC) {
            if (!hcode.contains(cl.hashCode())) {
                cl.print();
                hcode.add(cl.hashCode());
                assertEquals("->u1->u2->u3", cl.toString());
            }
        }

    }


    @Test
    void complexClique() throws UserNotFoundException, CurrentNodeDoesNotBelong {
        socialNetwork.addUser(u1);
        socialNetwork.addUser(u2);
        socialNetwork.addUser(u3);
        socialNetwork.addUser(u4);
        socialNetwork.addUser(u5);


        socialNetwork.addConnection(u1, u2);
        socialNetwork.addConnection(u1, u3);
        socialNetwork.addConnection(u1, u4);
        socialNetwork.addConnection(u2, u3);
        socialNetwork.addConnection(u2, u4);
        socialNetwork.addConnection(u3, u4);

        socialNetwork.addConnection(u3, u5);
        List<Clique> listC = socialNetwork.getAllCliques();

        Set<Integer> hcode = new HashSet<>();

        for (Clique cl : listC) {
            if (!hcode.contains(cl.hashCode())) {
                if (cl.getNodes().size() >= 4) {
                    cl.print();
                    assertEquals("->u1->u2->u3->u4", cl.toString());
                }
                hcode.add(cl.hashCode());
            }
        }

    }

}
