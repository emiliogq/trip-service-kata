package org.craftedsw.tripservicekata.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserShould {

    @Test
    void knowIfIsAFriendWithAnotherUser(){
        User bob = new User();
        User john = new User();
        User alice = new User();

        alice.addFriend(bob);

        assertFalse(bob.isFriend(john));

        assertTrue(alice.isFriend(bob));
        assertTrue(bob.isFriend(alice));
    }

}
