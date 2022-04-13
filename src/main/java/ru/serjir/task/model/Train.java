package ru.serjir.task.model;


import lombok.Getter;
import lombok.Setter;
import ru.serjir.task.entity.Station;

import java.util.List;
@Getter
@Setter
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



}

