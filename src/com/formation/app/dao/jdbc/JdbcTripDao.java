package com.formation.app.dao.jdbc;

import com.formation.app.dao.TripDao;

import java.util.List;

public class JdbcTripDao extends JdbceDao implements TripDao {
    @Override
    public Long createTrip(Object o) {
        return null;
    }

    @Override
    public Object read(Object o) {
        return null;
    }

    @Override
    public List findAllTrip() {
        return null;
    }

    @Override
    public Long findTripById(Long id) {
        return null;
    }

    @Override
    public boolean updateTrip(Object o) {
        return false;
    }

    @Override
    public boolean removeTrip(Object trip) {
        return false;
    }
}
