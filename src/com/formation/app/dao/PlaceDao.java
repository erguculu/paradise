package com.formation.app.dao;

import java.util.List;

public interface PlaceDao <ID, Place> {
    Place createPlace(Place place);
    List<Place> findAllPlace();
    Place findPlaceById(Long id);
    Boolean updatePlace(Place place);
    Boolean removePlace(ID id);

}
