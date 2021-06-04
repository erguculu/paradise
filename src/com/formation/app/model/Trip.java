package com.formation.app.model;

public class Trip {
   private Long id;
   private String departure;
   private String arrival;
   private Float price;
   private Long place_id;

    public Trip(String departure, String arrival, Float price, Long place_id) {
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
        this.place_id = place_id;
    }


    public Trip(Long id, String departure, String arrival, Float price, Long place_id) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
        this.place_id = place_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getPlace_id() {
        return place_id;
    }

    public void setPlace_id(Long place_id) {
        this.place_id = place_id;
    }
}
