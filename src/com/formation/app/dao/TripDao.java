package com.formation.app.dao;

import java.util.List;

public interface TripDao <ID, Trip> {
    Trip createTrip(Trip trip);
    List<Trip> findAllTrip();
    Trip findTripById(Long id);
    Boolean updateTrip(Trip trip);
    Boolean removeTrip(ID id);


}
