package com.formation.app.dao;

import java.util.List;

public interface TripDao <ID, Trip> {
    Long createTrip(Trip trip);
    Trip read(ID id);
    List<Trip> findAllTrip();
    Long findTripById(Long id);
    boolean updateTrip(Trip trip);
    boolean removeTrip(ID trip);


}
