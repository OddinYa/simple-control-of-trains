package ru.serjir.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.serjir.task.exception.StationNotFoundException;
import ru.serjir.task.exception.TrainCollisionException;
import ru.serjir.task.model.Train;
import ru.serjir.task.repository.RoadRepo;
import ru.serjir.task.repository.StationRepo;

import java.util.List;


@Service
public class TrainService {



    @Autowired
    StationRepo stationRepo;
    @Autowired
    RoadRepo RoadRepo;

    public List<Train> creationTrain(List<Train> trainList) throws TrainCollisionException, StationNotFoundException {
        int indexCollision = 0;
        for (int i = 0; i < trainList.size(); i++) {
            for (int j = i; j < trainList.size(); j++) {
                if (trainList.get(i).getIdStart() == trainList.get(j).getIdStart() && !trainList.get(i).
                        equals(trainList.get(j))) {
                    try {
                        trainList.get(i).setMessage("Столкновение");
                        trainList.get(j).setMessage("Столкновение");
                        indexCollision++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            trainList.get(i).setStartStation(stationRepo.findById(trainList.get(i).getIdStart()).get());
            trainList.get(i).setFinishStation(stationRepo.findById(trainList.get(i).getIdFinish()).get());
        }
        if (indexCollision > 0) {
            throw new TrainCollisionException("Столкновение на станции при старте");
        }
         //checkCollision(trainList);
        return trainList;
    }




}

