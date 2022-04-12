package ru.serjir.task.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.serjir.task.model.Train;

import ru.serjir.task.service.BuildGraph;
import ru.serjir.task.service.TrainService;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    TrainService trainService;
    @Autowired
    BuildGraph buildGraph;




 //  @GetMapping()
 //  public ResponseEntity conditionTrain() {
 //      try {


 //          List<Train> trainList = new LinkedList<>();
 //          Train train1 = new Train(1, 5);
 //          Train train2 = new Train(4, 8);
 //          Train train3 = new Train(10, 7);
 //          Train train4 = new Train(6, 1);
 //          trainList.add(train1);
 //          trainList.add(train2);
 //          trainList.add(train3);
 //          trainList.add(train4);

 //          return ResponseEntity.ok(trainService.creationTrain(trainList));
 //      } catch (TrainCollisionException e) {
 //          return ResponseEntity.badRequest().body(e.getMessage());
 //      } catch (NoSuchElementException e) {
 //          return ResponseEntity.badRequest().body(new StationNotFoundException("Станция не найдена"));
 //      } catch (Exception e) {
 //          return ResponseEntity.badRequest().body(e.toString());
 //      }
 //  }

    @GetMapping()
     public ResponseEntity conditionTrain() {
        try {
            List<Train> trainList = new LinkedList<>();
            Train train = new Train(1, 9);
            Train train2 = new Train(2, 6);
            trainList.add(train);
            trainList.add(train2);

            train.setRailways(Collections.singletonList(buildGraph.findTheWay().getPath(train.getIdStart(), train2.getIdFinish()).getEdgeList().toString()));
            train2.setRailways(Collections.singletonList(buildGraph.findTheWay().getPath(train2.getIdStart(),train2.getIdFinish()).toString()));

            return ResponseEntity.ok(trainService.creationTrain(trainList));


        }catch (Exception e){
          return   ResponseEntity.badRequest().body(e.toString());
        }
    }
}
