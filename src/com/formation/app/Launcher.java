package com.formation.app;

import com.formation.app.dao.jdbc.JdbcPlaceDao;
import com.formation.app.dao.jdbc.JdbcTripDao;
import com.formation.app.model.Place;
import com.formation.app.model.Trip;

import java.util.List;
import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {

        JdbcPlaceDao placeDao = new JdbcPlaceDao();
        System.out.println(placeDao.findPlaceById(1L).toString());

        Place place1 = new Place("Istanbul");
        Place maplace = placeDao.createPlace(place1);

        List<Place> places = placeDao.findAllPlace();
        for (Place w: places){
            System.out.println("============");
            System.out.println("Id: "+ w.getId());
            System.out.println("name: "+ w.getName());
        }

        JdbcTripDao tripDao = new JdbcTripDao();

        List<Trip> trips = tripDao.findAllTrip();
        for (Trip w: trips){
            System.out.println(w.getDeparture());

            Scanner scan = new Scanner(System.in);
            System.out.println("WELCOME ABOARD !");


        }



    }
}
