package com.formation.app.dao;


import com.formation.app.dao.jdbc.JdbcPlaceDao;
import com.formation.app.dao.jdbc.JdbcTripDao;

public class DaoFactory {
    private DaoFactory() {
    }
    public static JdbcTripDao getTripDao(){
        return new JdbcTripDao();
    }

    public static JdbcPlaceDao getPlaceDao(){
        return new JdbcPlaceDao();
    }
}
