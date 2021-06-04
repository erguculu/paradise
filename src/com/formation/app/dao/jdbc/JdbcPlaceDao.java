package com.formation.app.dao.jdbc;

import com.formation.app.dao.PlaceDao;
import com.formation.app.model.Place;
import com.formation.app.model.Trip;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPlaceDao extends JdbcDao implements PlaceDao<Long, Place> {


    @Override
    public Place createPlace(Place place) {
        Place addedPlace = null;
        Connection connection = this.connection;
        String createQuery = "INSERT INTO place (name) VALUE (?)";
        try (PreparedStatement pst = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, place.getName());
            pst.execute();

            ResultSet resultSet = pst.getGeneratedKeys();
            resultSet.next();

            Long id = resultSet.getLong(1);
            addedPlace = findPlaceById(id);

            connection.commit();
        } catch (SQLException e1) {
            e1.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public List<Place> findAllPlace() {
        List<Place> places = new ArrayList<>();
        String query = "SELECT * FROM place";
        Connection connection =this.connection;
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                places.add(mapToPlace(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return places;
    }

    @Override
    public Place findPlaceById(Long id) {
        Place place = null;
        Connection connection =this.connection;
        String query = "SELECT * FROM place WHERE id = ? ";
        try(PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()){
                place = mapToPlace(resultSet);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return place;
    }

    private Place mapToPlace(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        return new Place(id, name);
    }

    @Override
    public Boolean updatePlace(Place place) {
        Connection connection = this.connection;
        int updadeRows = 0;
        String query = "UPDATE trip SET ";
        try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setObject(1, place.getId());
            pst.setString(2, place.getName());

            pst.execute();

            updadeRows = pst.executeUpdate();

            connection.commit();

        } catch (SQLException e1) {
            e1.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return updadeRows > 0;
    }

    @Override
    public Boolean removePlace(Long id) {
        boolean isDeleted = false;
        Connection connection = this.connection;
        String query = "DELETE FROM place WHERE id = ?";
        try(PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setLong(1, id);
            isDeleted = pst.execute();
            connection.commit();

        } catch (SQLException e1) {
            e1.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return isDeleted;
    }
}
