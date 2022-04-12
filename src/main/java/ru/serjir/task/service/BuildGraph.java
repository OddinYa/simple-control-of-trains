package ru.serjir.task.service;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.serjir.task.entity.Road;
import ru.serjir.task.entity.Station;
import ru.serjir.task.repository.RoadRepo;
import ru.serjir.task.repository.StationRepo;


import java.io.Serializable;
import java.util.List;


@Service
public class BuildGraph implements Serializable {

    @Autowired
    StationRepo stationRepo;
    @Autowired
    RoadRepo roadRepo;
    @Autowired



    public DijkstraShortestPath<Station, DefaultWeightedEdge> findTheWay() {


        SimpleDirectedWeightedGraph<Station, DefaultWeightedEdge> dg = createDiGraph();
        DijkstraShortestPath<Station, DefaultWeightedEdge> dp = new DijkstraShortestPath<>(dg);

        return dp;
    }

    private SimpleDirectedWeightedGraph<Station, DefaultWeightedEdge> createDiGraph() {

        SimpleDirectedWeightedGraph<Station, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        List<Station> stations = stationRepo.findAll();

        stations.forEach(graph::addVertex);

        List<Road> roads = roadRepo.findAll();


        roads.forEach(road -> {
            List<Station> stations1 = road.getStations();
            DefaultWeightedEdge e1 = graph.addEdge(stations1.get(0), stations1.get(1));
            graph.setEdgeWeight(e1, road.getLenght());
        });

        return graph;}


}
