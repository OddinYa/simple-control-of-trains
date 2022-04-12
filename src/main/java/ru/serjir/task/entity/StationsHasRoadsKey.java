package ru.serjir.task.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

@Embeddable
class StationsHasRoadsKey implements Serializable {

    @Column(name = "stations_id")
   private Integer stationsId;

    @Column(name = "roads_id")
   private  Integer roadsId;

    public StationsHasRoadsKey() {
    }

    public Integer getStationId() {
        return stationsId;
    }

    public void setStationId(Integer stationId) {
        this.stationsId = stationId;
    }

    public Integer getRoadsId() {
        return roadsId;
    }

    public void setRoadsId(Integer roadsId) {
        this.roadsId = roadsId;
    }
}