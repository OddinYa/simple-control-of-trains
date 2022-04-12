package ru.serjir.task.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stations")
public class Station {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

 //   @ManyToMany
 //   @JoinTable(
 //           name = "stations_has_roads",
 //           joinColumns = @JoinColumn(name = "stations_id"),
 //           inverseJoinColumns = @JoinColumn(name = "roads_id"))

    @OneToMany(mappedBy = "station")
    private Set<StationsHasRoads> stationsHasRoads;

    public Set<StationsHasRoads> getStationsHasRoads() {
        return stationsHasRoads;
    }

    public void setStationsHasRoads(Set<StationsHasRoads> stationsHasRoads) {
        this.stationsHasRoads = stationsHasRoads;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
