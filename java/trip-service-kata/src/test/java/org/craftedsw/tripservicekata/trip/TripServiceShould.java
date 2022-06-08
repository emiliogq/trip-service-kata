package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TripServiceShould {

    private static final User REGISTERED = new User();
    private User loggedUser;

    @Test
	void throwUserNotLoggedInWithANonLoggedUser(){
        TripService tripService = new TestableTripService();
        this.loggedUser = null;
        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(null));
    }

    @Test
    void getNoTripsWithAUserWithoutFriends(){
        TripService tripService = new TestableTripService();
        User anonymous = new User();
        this.loggedUser = REGISTERED;
        List<Trip> trips = tripService.getTripsByUser(anonymous);
        assertTrue(trips.isEmpty());
    }

    @Test
    void getNoTripsWithANonFriendOfLoggedUser(){
        TripService tripService = new TestableTripService();
        User anonymous = new User();
        User stranger = new User();
        anonymous.addFriend(stranger);
        this.loggedUser = REGISTERED;
        List<Trip> trips = tripService.getTripsByUser(anonymous);
        assertTrue(trips.isEmpty());
    }

    protected class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser(){
            return loggedUser;
        }
    }
}
