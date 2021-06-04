package com.formation.app.dao;

import java.util.List;

public interface PlaceDao <ID, Place> {
    Long createPlace(Place place);
    Place read (ID id);
    List<Place> findAllPlace();
    Long findPlaceById(Long id);
    boolean updatePlace(Place place);
    boolean removePlace(ID id);

}
