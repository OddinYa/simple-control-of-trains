package ru.serjir.task.service;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
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

    public DijkstraShortestPath<Integer, DefaultWeightedEdge> findTheWay() {


        SimpleWeightedGraph<Integer, DefaultWeightedEdge> dg = createGraph();


        return new DijkstraShortestPath<>(dg);
    }

    private SimpleWeightedGraph<Integer, DefaultWeightedEdge> createGraph() {

        SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        List<Station> stations = stationRepo.findAll();
        stations.stream()
                .map(Station::getId)
                .forEach(graph::addVertex);



        List<Road> roads = roadRepo.findAll();
        roads.forEach(road -> {
            List<Station> stations1 = road.getStations();
            DefaultWeightedEdge e1 = graph.addEdge(stations1.get(0).getId(), stations1.get(1).getId());
            graph.setEdgeWeight(e1, road.getLength());
        });


        return graph;}


}
