package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TripServiceShould {

    private static final User REGISTERED = new User();
    private static final Trip BARCELONA = new Trip("Barcelona");
    private User loggedUser;
    private User anonymous;
    private User stranger;
    private TripService tripService;

    @BeforeEach
    void setUp() {
        anonymous = new User();
        stranger = new User();
        tripService = new TestableTripService();
    }

    @Test
	void throwUserNotLoggedInWithANonLoggedUser(){
        TripService tripService = new TestableTripService();
        this.loggedUser = null;
        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(null));
    }

    @Test
    void getNoTripsWithAUserWithoutFriends(){
        TripService tripService = new TestableTripService();
        this.loggedUser = REGISTERED;
        List<Trip> trips = tripService.getTripsByUser(anonymous);
        assertTrue(trips.isEmpty());
    }

    @Test
    void getNoTripsWithANonFriendOfLoggedUser(){
        anonymous.addFriend(stranger);
        this.loggedUser = REGISTERED;

        List<Trip> trips = tripService.getTripsByUser(anonymous);
        assertTrue(trips.isEmpty());
    }

    @Test
    void getTripsWithAFriendOfLoggedUser(){
        User friend = new User();
        friend.addFriend(REGISTERED);
        friend.addTrip(BARCELONA);

        this.loggedUser = REGISTERED;

        List<Trip> trips = tripService.getTripsByUser(friend);
        assertTrue(trips.contains(BARCELONA));
    }

    protected class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser(){
            return loggedUser;
        }

        @Override
        protected List<Trip> tripsBy(User user) {
            return user.trips();
        }
    }
}
