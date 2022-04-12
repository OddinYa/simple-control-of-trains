package ru.serjir.task.entity;

import javax.persistence.*;

@Entity
public class StationsHasRoads {

    @EmbeddedId
    StationsHasRoadsKey id;

    @ManyToOne
    @MapsId("stationsId")
    @JoinColumn(name = "stations_id")
    Station station;

    @ManyToOne
    @MapsId("roadsId")
    @JoinColumn(name = "roads_id")
    Road road;

    public StationsHasRoadsKey getId() {
        return id;
    }

    public Station getStation() {
        return station;
    }

    public Road getRoad() {
        return road;
    }

    public void setId(StationsHasRoadsKey id) {
        this.id = id;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public void setRoad(Road road) {
        this.road = road;
    }
}
