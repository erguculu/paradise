package com.formation.app.dao.jdbc;
import com.formation.app.dao.TripDao;
import com.formation.app.model.Place;
import com.formation.app.model.Trip;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTripDao extends JdbcDao implements TripDao<Long, Trip> {

    @Override
    public Trip createTrip(Trip tripToCreate) {
        Trip addedTrip = null;
        Connection connection = this.connection;
        String createQuery = "INSERT INTO trip (departure, arrival, price, trip_id) VALUES (?, ?, ?, ?)";
        try(PreparedStatement pst = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, addedTrip.getDeparture());
            pst.setString(2, addedTrip.getArrival());
            pst.setFloat(3, addedTrip.getPrice());
            pst.setLong(4, addedTrip.getPlace_id());
            pst.execute();

            ResultSet resultSet = pst.getGeneratedKeys();
            resultSet.next();

            Long id = resultSet.getLong(1);
            addedTrip = findTripById(id);

            connection.commit();
        } catch (SQLException e1) {
            e1.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

        return addedTrip;
    }

    @Override
    public List<Trip> findAllTrip() {
        List<Trip>  trips = new ArrayList<>();
        String query = "SELECT * FROM trip";
        Connection connection =this.connection;
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                trips.add(mapToTrip(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    @Override
    public Trip findTripById(Long id) {
        Trip trip = null;
        Connection connection =this.connection;
        String query = "SELECT * FROM trip WHERE id = ? ";
        try(PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()){
                trip = mapToTrip(resultSet);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return trip;
    }

    private Trip mapToTrip(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String departure = resultSet.getString("departure");
        String arrival = resultSet.getString("departure");
        Float price = resultSet.getFloat("price");
        Long place_id = resultSet.getLong("place_id");
        return new Trip(departure, arrival,price, place_id );
    }

    @Override
    public Boolean updateTrip(Trip trip) {
        Connection connection = this.connection;
        int updadeRows = 0;
        String query = "UPDATE trip SET ";
        try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setObject(1, trip.getId());
            pst.setString(2, trip.getArrival());
            pst.setString(3, trip.getDeparture());
            pst.setLong(4, trip.getPlace_id());

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
    public Boolean removeTrip(Long id) {
        boolean isDeleted = false;
        Connection connection = this.connection;

        String query = "DELETE FROM trip WHERE id = ?";
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
