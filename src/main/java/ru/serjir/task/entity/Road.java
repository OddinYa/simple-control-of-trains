package ru.serjir.task.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roads")
public class Road   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer lenght;



    @OneToMany(mappedBy = "road")
    private Set<StationsHasRoads> stationsHasRoads;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLenght() {
        return lenght;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }

    public Set<StationsHasRoads> getStationsHasRoads() {
        return stationsHasRoads;
    }

    public void setStationsHasRoads(Set<StationsHasRoads> stationsHasRoads) {
        this.stationsHasRoads = stationsHasRoads;
    }


}

