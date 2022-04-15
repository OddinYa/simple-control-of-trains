package ru.serjir.task.service;

import org.graalvm.compiler.graph.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.serjir.task.model.Train;
import ru.serjir.task.repository.RoadRepo;
import ru.serjir.task.repository.StationRepo;

import java.util.ArrayList;
import java.util.List;


@Service
public class TrainService {

    @Autowired
    StationRepo stationRepo;
    @Autowired
    RoadRepo RoadRepo;


    public List<Train> checkCollision(BuildGraph graph) {
        //Test trains;
        Train train1 = new Train(1, 4);
        Train train2 = new Train(2, 7);
        Train train3 = new Train(9, 6);
        Train train4 = new Train(5, 8);
        Train train5 = new Train(2, 8);

        List<Train> trainList = new ArrayList<>();  // сюда идет репа
        trainList.add(train1);
        trainList.add(train2);
        trainList.add(train3);
        trainList.add(train4);
        trainList.add(train5);
        //Test
        List<Train> reternList = new ArrayList<>();
        List<Train> verified = new ArrayList<>();
        List<Train> notVerified = new ArrayList<>();

        for (int i = 0; i < trainList.size(); i++) {
            for (int j = i + 1; j < trainList.size(); j++) {
                boolean weight = (int) graph.findTheWay()
                        .getPath(trainList.get(i).getIdStart(), trainList.get(i).getIdFinish()).getWeight()
                        == (int) graph.findTheWay().
                        getPath(trainList.get(j).getIdStart(), trainList.get(j).getIdFinish()).getWeight();

                boolean start = trainList.get(i).getIdStart().equals(trainList.get(j).getIdStart());
                boolean finish = trainList.get(j).getIdFinish().equals(trainList.get(i).getIdFinish());

                if (start) {
                    notVerified.add(trainList.get(i));
                    trainList.get(i).setMessage("Столкновение");
                    notVerified.add(trainList.get(j));
                    trainList.get(j).setMessage("Столкновение");


                } else if (finish && weight) {
                    notVerified.add(trainList.get(i));
                    trainList.get(i).setMessage("Столкновение");
                    notVerified.add(trainList.get(j));
                    trainList.get(j).setMessage("Столкновение");
                }
                else if(trainList.get(i).getIdStart().equals(trainList.get(j).getIdFinish())
                        && weight) {
                    notVerified.add(trainList.get(i));
                    trainList.get(i).setMessage("Столкновение");
                    notVerified.add(trainList.get(j));
                    trainList.get(j).setMessage("Столкновение");
                }


            }if(trainList.get(i).getMessage().equals("Маршрут завершен")){
                verified.add(trainList.get(i));
            }

        }
        reternList.addAll(checkMiddleCollision(verified,graph));
        reternList.addAll(notVerified);
        return reternList;
    }

    private List<Train> checkMiddleCollision(List<Train> verified,BuildGraph graph){

        for (int i = 0; i < verified.size(); i++) {
            for (int j = 1; j < verified.size(); j++) {

                Integer stationI = graph.findTheWay()
                        .getPath(verified.get(i).getIdStart(),verified.get(i).getIdFinish())
                        .getVertexList()
                        .get(i);
                Integer stationJ = graph.findTheWay()
                        .getPath(verified.get(j).getIdStart(),verified.get(j).getIdFinish())
                        .getVertexList()
                        .get(j);



                if(stationI.equals(stationJ)){
                    boolean weight = (int) graph.findTheWay()
                            .getPath(verified.get(i).getIdStart(), stationI).getWeight()
                            == (int) graph.findTheWay().
                            getPath(verified.get(j).getIdStart(), stationJ).getWeight();
                    if(weight){
                        verified.get(i).setMessage("Столкновение");
                        verified.get(j).setMessage("Столкновение");
                    }
                }
            }

        }
        return verified;
    }


}

