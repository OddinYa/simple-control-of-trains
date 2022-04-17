package ru.serjir.task.service;


import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.serjir.task.entity.Station;
import ru.serjir.task.entity.Train;
import ru.serjir.task.repository.RoadRepo;
import ru.serjir.task.repository.StationRepo;
import ru.serjir.task.repository.TrainRepo;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TrainServiceTest {

    @InjectMocks
    private TrainService trainService;

    @MockBean
    private RoadRepo roadRepo;
    @MockBean
    private StationRepo stationRepo;
    @MockBean
    private BuildGraph graph;
    @MockBean
    private TrainRepo trainRepo;
    @MockBean
    private Train train;


    @Test
    void checkOneTrain() {

        Mockito.when(graph.findTheWay()).thenReturn(createGraph());
        Mockito.when(trainRepo.findAll()).thenReturn(oneTrains());
        List<Train> expected = oneTrainsExpented();
        List<Train> actual = trainService.checkCollision();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkTwoTrainsCollideBetweenStations() {

        Mockito.when(graph.findTheWay()).thenReturn(createGraph());
        Mockito.when(trainRepo.findAll()).thenReturn(twoTrainsCollideBetweenStations());
        List<Train> expected = twoTrainsCollideBetweenStationsExpented();
        List<Train> actual = trainService.checkCollision();

        Assertions.assertEquals(expected, actual);
    } //

    @Test
    void checkTwoTrainsCollideOnStart() {

        Mockito.when(graph.findTheWay()).thenReturn(createGraph());
        Mockito.when(trainRepo.findAll()).thenReturn(twoTrainsCollideOnStart());

        List<Train> expected = twoTrainsCollideOnStartExpented();
        List<Train> actual = trainService.checkCollision();

        Assertions.assertEquals(expected, actual);
    } //

    @Test
    void checkTwoTrainsCollideOnArrival() {

        Mockito.when(graph.findTheWay()).thenReturn(createGraph());
        Mockito.when(trainRepo.findAll()).thenReturn(twoTrainsCollideOnArrival());
        List<Train> expected = twoTrainsCollideOnArrivalExpented();
        List<Train> actual = trainService.checkCollision();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkNoCollisions() {

        Mockito.when(graph.findTheWay()).thenReturn(createGraph());
        Mockito.when(trainRepo.findAll()).thenReturn(noCollisions());
        List<Train> expected = noCollisionsExpented();
        List<Train> actual = trainService.checkCollision();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkTwoTrainsPathsIntersection() {
        Mockito.when(graph.findTheWay()).thenReturn(createGraph());
        Mockito.when(trainRepo.findAll()).thenReturn(twoTrainsPathsIntersection());
        List<Train> expected = twoTrainsPathsIntersectionExpented();
        List<Train> actual = trainService.checkCollision();

        Assertions.assertEquals(expected, actual);
    }

    private List<Train> oneTrainsExpented() {

        List<Train> trains = new ArrayList<>();

        Station station = Station.builder().id(3).name("C").build();
        Station station2 = Station.builder().id(1).name("A").build();

        Train train = Train.builder().id(5).stationStart(station).stationFinish(station2).build();

        trains.add(train);

        return trains;

    }
    private List<Train> oneTrains() {

        List<Train> trains = new ArrayList<>();

        Station station = Station.builder().id(3).name("C").build();
        Station station2 = Station.builder().id(1).name("A").build();

        Train train = Train.builder().id(5).stationStart(station).stationFinish(station2).build();

        trains.add(train);

        return trains;

    }

    private List<Train> twoTrainsCollideBetweenStationsExpented() {

        List<Train> trains = new ArrayList<>();

        Station station = Station.builder().id(1).name("A").build();
        Station station2 = Station.builder().id(3).name("C").build();

        Train train = Train.builder().id(1).stationStart(station).stationFinish(station2).info("Столкновение").build();

        Station station3 = Station.builder().id(3).name("C").build();
        Station station4 = Station.builder().id(1).name("A").build();

        Train train2 = Train.builder().id(2).stationStart(station3).stationFinish(station4).info("Столкновение").build();

        trains.add(train);
        trains.add(train2);

        return trains;

    }
    private List<Train> twoTrainsCollideBetweenStations() {

        List<Train> trains = new ArrayList<>();

        Station station = Station.builder().id(1).name("A").build();
        Station station2 = Station.builder().id(3).name("C").build();

        Train train = Train.builder().id(1).stationStart(station).stationFinish(station2).build();

        Station station3 = Station.builder().id(3).name("C").build();
        Station station4 = Station.builder().id(1).name("A").build();

        Train train2 = Train.builder().id(2).stationStart(station3).stationFinish(station4).build();

        trains.add(train);
        trains.add(train2);

        return trains;

    }

    private List<Train> twoTrainsCollideOnStartExpented(){
        List<Train> trains = new ArrayList<>();

        Station station = Station.builder().id(3).name("C").build();
        Station station2 = Station.builder().id(1).name("A").build();

        Train train = Train.builder().id(1).stationStart(station).stationFinish(station2).info("Столкновение").build();

        Station station3 = Station.builder().id(3).name("C").build();
        Station station4 = Station.builder().id(4).name("D").build();

        Train train2 = Train.builder().id(2).stationStart(station3).stationFinish(station4).info("Столкновение").build();

        trains.add(train);
        trains.add(train2);

        return trains;
    }
    private List<Train> twoTrainsCollideOnStart() {

        List<Train> trains = new ArrayList<>();

        Station station = Station.builder().id(3).name("C").build();
        Station station2 = Station.builder().id(1).name("A").build();

        Train train = Train.builder().id(1).stationStart(station).stationFinish(station2).build();

        Station station3 = Station.builder().id(3).name("C").build();
        Station station4 = Station.builder().id(4).name("D").build();

        Train train2 = Train.builder().id(2).stationStart(station3).stationFinish(station4).build();

        trains.add(train);
        trains.add(train2);

        return trains;

    }

    private List<Train> twoTrainsCollideOnArrivalExpented() {

        List<Train> trains = new ArrayList<>();

        Station station = Station.builder().id(3).name("C").build();
        Station station2 = Station.builder().id(1).name("A").build();

        Train train = Train.builder().id(1).stationStart(station).stationFinish(station2).info("Столкновение").build();

        Station station3 = Station.builder().id(3).name("C").build();
        Station station4 = Station.builder().id(5).name("D").build();

        Train train2 = Train.builder().id(2).stationStart(station3).stationFinish(station4).info("Столкновение").build();

        trains.add(train);
        trains.add(train2);

        return trains;
    }
    private List<Train> twoTrainsCollideOnArrival() {

        List<Train> trains = new ArrayList<>();

        Station station = Station.builder().id(3).name("C").build();
        Station station2 = Station.builder().id(1).name("A").build();

        Train train = Train.builder().id(1).stationStart(station).stationFinish(station2).build();

        Station station3 = Station.builder().id(3).name("C").build();
        Station station4 = Station.builder().id(5).name("D").build();

        Train train2 = Train.builder().id(2).stationStart(station3).stationFinish(station4).build();

        trains.add(train);
        trains.add(train2);

        return trains;
    }

    private List<Train> noCollisionsExpented() {

        List<Train> trains = new ArrayList<>();

        Station station = Station.builder().id(1).name("A").build();
        Station station2 = Station.builder().id(2).name("B").build();

        Train train = Train.builder().id(1).stationStart(station).stationFinish(station2).info("").build();

        Station station3 = Station.builder().id(5).name("D").build();
        Station station4 = Station.builder().id(3).name("C").build();

        Train train2 = Train.builder().id(2).stationStart(station3).stationFinish(station4).info("").build();

        trains.add(train);
        trains.add(train2);

        return trains;
    }
    private List<Train> noCollisions() {

        List<Train> trains = new ArrayList<>();

        Station station = Station.builder().id(1).name("A").build();
        Station station2 = Station.builder().id(2).name("B").build();

        Train train = Train.builder().id(1).stationStart(station).stationFinish(station2).build();

        Station station3 = Station.builder().id(5).name("D").build();
        Station station4 = Station.builder().id(3).name("C").build();

        Train train2 = Train.builder().id(2).stationStart(station3).stationFinish(station4).build();

        trains.add(train);
        trains.add(train2);

        return trains;
    }

    private List<Train> twoTrainsPathsIntersectionExpented() {

        List<Train> trains = new ArrayList<>();

        Station station = Station.builder().id(1).name("A").build();
        Station station2 = Station.builder().id(4).name("D").build();

        Train train = Train.builder().id(1).stationStart(station).stationFinish(station2).info("Столкновение").build();

        Station station3 = Station.builder().id(5).name("E").build();
        Station station4 = Station.builder().id(2).name("B").build();

        Train train2 = Train.builder().id(2).stationStart(station3).stationFinish(station4).info("Столкновение").build();

        trains.add(train);
        trains.add(train2);

        return trains;

    }
    private List<Train> twoTrainsPathsIntersection() {

        List<Train> trains = new ArrayList<>();

        Station station = Station.builder().id(1).name("A").build();
        Station station2 = Station.builder().id(4).name("D").build();

        Train train = Train.builder().id(1).stationStart(station).stationFinish(station2).build();

        Station station3 = Station.builder().id(5).name("E").build();
        Station station4 = Station.builder().id(2).name("B").build();

        Train train2 = Train.builder().id(2).stationStart(station3).stationFinish(station4).build();

        trains.add(train);
        trains.add(train2);

        return trains;

    }




    private DijkstraShortestPath createGraph() {

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
        graph.setEdgeWeight(e5, 100);
        DefaultWeightedEdge e6 = graph.addEdge(4, 5);
        graph.setEdgeWeight(e6, 80);

        return new DijkstraShortestPath(graph);
    }


}