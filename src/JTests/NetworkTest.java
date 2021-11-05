package JTests;

import DS.Network;
import Data.User;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {

    Network net = Network.getInstance();
    HashMap<String, User> network = net.getNetwork();
    User u1 = new User("u1");
    User u2 = new User("u2");

    @BeforeEach
    void tearUp() {
        Network net = Network.getInstance();
        HashMap<String, User> network = net.getNetwork();
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
        net.addFriend(u1, u2);
        net.addFriend(u2, u1);
        HashSet<User> eU1 = net.getGraph().getSingleFList(u1);
        HashSet<User> eU2 = net.getGraph().getSingleFList(u2);
        assertTrue(eU1.contains(u2) && eU2.contains(u1));

    }
}
