package ru.serjir.task.service;


import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.serjir.task.entity.Station;
import ru.serjir.task.entity.Train;
import ru.serjir.task.repository.RoadRepo;
import ru.serjir.task.repository.StationRepo;
import ru.serjir.task.repository.TrainRepo;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class TrainServiceTest {


    @Mock
    private RoadRepo roadRepo;
    @Mock
    private StationRepo stationRepo;
    @Mock
    private BuildGraph graph;
    @Mock
    private TrainRepo trainRepo;

    @InjectMocks
    TrainService trainService;

    @Test
    void checkCollision() {

        Mockito.when(trainRepo.findAll()).thenReturn(trains());
        List<Train> expected = trains();

        Mockito.when(graph.findTheWay()).thenReturn(createGraph());
        List<Train> actual = trainService.checkCollision();


        Assertions.assertEquals(expected, actual);


    }

    private List<Train> trains (){



        List<Train> trains = new ArrayList<>();
       // Train train1 = Train.builder().stationStart(new Station()).stationFinish(new Station()).build();
        Train train = new Train();

        train.setStationStart();
        trains.add(train);
        trains.add(train1);
        return trains;
    }




    private DijkstraShortestPath<Integer, DefaultWeightedEdge> createGraph() {

        SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);


        DefaultWeightedEdge e1 = graph.addEdge(1, 2);
        graph.setEdgeWeight(e1, 100);
        DefaultWeightedEdge e2 = graph.addEdge(1, 3);
        graph.setEdgeWeight(e2, 100);
        DefaultWeightedEdge e3 = graph.addEdge(2, 3);
        graph.setEdgeWeight(e3, 160);

        DefaultWeightedEdge e4 = graph.addEdge(3, 4);
        graph.setEdgeWeight(e4, 40);
        DefaultWeightedEdge e5 = graph.addEdge(3, 5);
        graph.setEdgeWeight(e5, 64);
        DefaultWeightedEdge e6 = graph.addEdge(4, 5);
        graph.setEdgeWeight(e6, 80);

        return new DijkstraShortestPath(graph);
    }


}