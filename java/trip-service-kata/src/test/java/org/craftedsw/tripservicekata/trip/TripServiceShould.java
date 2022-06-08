package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TripServiceShould {

    @Test
	void throwUserNotLoggedInWithANonLoggedUser(){
        TripService tripService = new TestableTripService();

        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(null));
    }

    protected class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser(){
            return null;
        }
    }
}
