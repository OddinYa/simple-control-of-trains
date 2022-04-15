package ru.serjir.task.model;


import lombok.Getter;
import lombok.Setter;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;


import java.util.List;
@Getter
@Setter
public class Train {

    private int speed = 65;
    private Integer idStart;
    private Integer idFinish;
    private String message = "Маршрут завершен";

    private List<DefaultWeightedEdge>railways = null;

//Test
    public Train(Integer idStart, Integer idFinish) {
        this.idStart = idStart;
        this.idFinish = idFinish;

    }
//Test


}

