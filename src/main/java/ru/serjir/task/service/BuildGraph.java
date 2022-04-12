package ru.serjir.task.service;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.serjir.task.entity.Road;
import ru.serjir.task.entity.Station;
import ru.serjir.task.entity.StationsHasRoads;
import ru.serjir.task.repository.RoadRepo;
import ru.serjir.task.repository.StationRepo;
import ru.serjir.task.repository.StationsHasRoadsRepo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class BuildGraph implements Serializable {

    @Autowired
    StationRepo stationRepo;
    @Autowired
    RoadRepo roadRepo;
    @Autowired
    StationsHasRoadsRepo stationsHasRoadsRepo;


    public DijkstraShortestPath<Station,DefaultWeightedEdge> findTheWay() {


        SimpleDirectedWeightedGraph<Station, DefaultWeightedEdge> dg = createDiGraph();
        DijkstraShortestPath<Station,DefaultWeightedEdge> dp = new DijkstraShortestPath<>(dg);

        return dp;
    }

    private SimpleDirectedWeightedGraph<Station, DefaultWeightedEdge> createDiGraph() {

        SimpleDirectedWeightedGraph<Station, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        List<Station> stations = (List<Station>) stationRepo.findAll();

        stations.forEach(graph::addVertex);

        Station station = stations.get(1);
        Set<StationsHasRoads> stationsHasRoads = station.getStationsHasRoads();

        getRoads(station, stationsHasRoads);

        Road road = new Road();

        stationsHasRoadsRepo.findById(1);
        graph.addEdge(road.getStationsHasRoads());


        DefaultWeightedEdge e1 = graph.addEdge(1, 2,l);
        graph.setEdgeWeight(e1, 150);

        DefaultWeightedEdge e2 = graph.addEdge(1, 5);
        graph.setEdgeWeight(e2, 240);

        DefaultWeightedEdge e3 = graph.addEdge(2, 4);
        graph.setEdgeWeight(e3, 30);

        DefaultWeightedEdge e4 = graph.addEdge(4, 5);
        graph.setEdgeWeight(e4, 40);

        DefaultWeightedEdge e5 = graph.addEdge(5, 7);
        graph.setEdgeWeight(e5, 100);

        DefaultWeightedEdge e6 = graph.addEdge(2, 3);
        graph.setEdgeWeight(e6, 60);

        DefaultWeightedEdge e7 = graph.addEdge(3, 6);
        graph.setEdgeWeight(e7, 100);

        DefaultWeightedEdge e8 = graph.addEdge(6, 7);
        graph.setEdgeWeight(e8, 140);

        DefaultWeightedEdge e9 = graph.addEdge(7, 8);
        graph.setEdgeWeight(e9, 140);

        DefaultWeightedEdge e10 = graph.addEdge(7, 9);
        graph.setEdgeWeight(e10, 130);

        DefaultWeightedEdge e11 = graph.addEdge(9, 10);
        graph.setEdgeWeight(e11, 140);


        return graph;
    }

    private List<Road> getRoads(Station station, Set<StationsHasRoads> stationsHasRoads) {
         return  stationsHasRoads.stream()
                .filter(st -> st.getStation().equals(station))
                .map(StationsHasRoads::getRoad)
                .collect(Collectors.toList());

    }
}
