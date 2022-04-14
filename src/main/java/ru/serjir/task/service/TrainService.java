package ru.serjir.task.service;

import org.graalvm.compiler.graph.Graph;
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
        Train train2 = new Train(5, 7);
        Train train3 = new Train(9, 7);
        Train train4 = new Train(6, 4);

        List<Train> trainList = new ArrayList<>();
        trainList.add(train1);
        trainList.add(train2);
        trainList.add(train3);
        trainList.add(train4);
        //Test

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
                trainList.get(i).setRailways(graph.findTheWay()
                        .getPath(trainList.get(i).getIdStart(),
                                trainList.get(i).getIdFinish()).getEdgeList());
                verified.add(trainList.get(i));
            }

        }
        return checkMiddleCollision(verified);
    }

    private List<Train> checkMiddleCollision(List<Train> verified){

        for (int i = 1; i < verified.size()-1; i++) {
            for (int j = 2; j < verified.size()-1; j++) {

            }

        }

    }


}

