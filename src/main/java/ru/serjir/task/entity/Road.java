package ru.serjir.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roads")
public class Road {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer length;

    @JsonIgnoreProperties("roads")
    @ManyToMany
    @JoinTable(
            name = "stations_has_roads",
            joinColumns = @JoinColumn(name = "roads_id"),
            inverseJoinColumns = @JoinColumn(name = "stations_id"))
    private List<Station> stations ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLenght() {
        return length;
    }

    public void setLenght(Integer lenght) {
        this.length = lenght;
    }

  public List<Station> getStations() {
      return stations;
  }

  public void setStations(List<Station> stations) {
      this.stations = stations;
   }
}

