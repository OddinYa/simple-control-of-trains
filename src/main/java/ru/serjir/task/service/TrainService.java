package ru.serjir.task.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.serjir.task.entity.Train;
import ru.serjir.task.repository.TrainRepo;

import java.util.ArrayList;
import java.util.List;


@Service
public class TrainService {


    @Autowired
    BuildGraph graph;
    @Autowired
    TrainRepo trainRepo;


    public List<Train> checkCollision() {

        Train trainI;
        Train trainJ;

        List<Train> trainList = trainRepo.findAll();  // сюда идет репа
        List<Train> reternList = new ArrayList<>();
        List<Train> verified = new ArrayList<>();
        List<Train> notVerified = new ArrayList<>();
        if(trainList.size()==1){
            return trainList;
        }

        for (int i = 0; i < trainList.size(); i++) {

            trainI = trainList.get(i);

            for (int j = i + 1; j < trainList.size(); j++) {

                trainJ = trainList.get(j);

                if (checkStart(trainList, i, j)) {
                    notVerified.add(trainI);
                    trainI.setInfo("Столкновение");
                    notVerified.add(trainJ);
                    trainJ.setInfo("Столкновение");
                } else if (checkFinish(trainList, i, j)) {

                    if (checkWeight(trainList, i, j)) {
                        notVerified.add(trainI);
                        trainI.setInfo("Столкновение");
                        notVerified.add(trainJ);
                        trainJ.setInfo("Столкновение");
                    }
                } else if (checkCross(trainList, i, j)) {
                    notVerified.add(trainI);
                    trainI.setInfo("Столкновение");
                    notVerified.add(trainJ);
                    trainJ.setInfo("Столкновение");
                }
            }
            if (trainI.getInfo().equals("")) {
               // trainI.setStations((graph.findTheWay()
               //         .getPath(trainI.getStationStart().getId(), trainI.getStationFinish().getId())
               //         .getVertexList()));
                verified.add(trainI);
            }

        }
        reternList.addAll(checkMiddleCollision(verified, graph));
        reternList.addAll(notVerified);
        return reternList;
    }

    private List<Train> checkMiddleCollision(List<Train> verified, BuildGraph graph) {

        Integer stationI;
        Integer stationJ;

        Integer stationIStart;
        Integer stationJStart;
        Integer stationIFinish;
        Integer stationJFinish;

        for (int i = 0; i < verified.size(); i++) {
            for (int j = i + 1; j < verified.size(); j++) {

                stationIStart = verified.get(i).getStationStart().getId();
                stationIFinish = verified.get(i).getStationFinish().getId();
                stationJStart = verified.get(j).getStationStart().getId();
                stationJFinish = verified.get(j).getStationFinish().getId();

                stationI = graph.findTheWay()
                        .getPath(stationIStart, stationIFinish)
                        .getVertexList()
                        .get(i);
                stationJ = graph.findTheWay()
                        .getPath(stationJStart, stationJFinish)
                        .getVertexList()
                        .get(j);


                if (stationI.equals(stationJ)) {
                    boolean weight = checkWeight(verified,i,j);
                    if (weight) {
                        verified.get(i).setInfo("Столкновение");
                        verified.get(j).setInfo("Столкновение");
                    }
                }
            }

        }
        return verified;
    }

    private boolean checkWeight(List<Train> trainList, int i, int j) {
        Train trainI = trainList.get(i);
        Train trainJ = trainList.get(j);

        Integer stationIStart = trainI.getStationStart().getId();
        Integer stationJStart = trainJ.getStationStart().getId();
        Integer stationIFinish = trainI.getStationFinish().getId();
        Integer stationJFinish = trainJ.getStationFinish().getId();

        boolean weight = (int) graph.findTheWay()
                .getPath(stationIStart, stationIFinish).getWeight()
                == (int) graph.findTheWay().
                getPath(stationJStart, stationJFinish).getWeight();

        return weight;
    }

    private boolean checkStart(List<Train> trainList, int i, int j) {
        Train trainI = trainList.get(i);
        Train trainJ = trainList.get(j);

        boolean start = trainI.getStationStart().equals(trainJ.getStationStart());
        return start;
    }

    private boolean checkFinish(List<Train> trainList, int i, int j) {
        Train trainI = trainList.get(i);
        Train trainJ = trainList.get(j);

        boolean finish = trainI.getStationFinish().equals(trainJ.getStationFinish());
        return finish;
    }

    private boolean checkCross(List<Train> trainList, int i, int j) {
        Train trainI = trainList.get(i);
        Train trainJ = trainList.get(j);

        boolean cross = trainI.getStationStart().equals(trainJ.getStationFinish());
        return cross;
    }


}

