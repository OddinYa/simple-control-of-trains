package ru.serjir.task.model;

import org.jgrapht.graph.DefaultWeightedEdge;
import ru.serjir.task.entity.Station;

import java.util.List;

public class Train {

    private int speed = 65;
    private Station startStation;
    private Station finishStation;
    private Integer idStart;
    private Integer idFinish;
    private String message = "Маршрут завершен";



    private List<String>railways = null;


    public Train(Integer idStart, Integer idFinish) {
        this.idStart = idStart;
        this.idFinish = idFinish;

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getFinishStation() {
        return finishStation;
    }

    public void setFinishStation(Station finishStation) {
        this.finishStation = finishStation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getIdStart() {
        return idStart;
    }


    public Integer getIdFinish() {
        return idFinish;
    }

    public List<String> getRailways() {
        return railways;
    }

    public void setRailways(List<String> railways) {
        this.railways = railways;
    }

}

