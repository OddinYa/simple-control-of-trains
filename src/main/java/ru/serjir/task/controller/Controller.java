package ru.serjir.task.controller;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.serjir.task.entity.Road;
import ru.serjir.task.entity.Station;
import ru.serjir.task.model.Train;
import ru.serjir.task.repository.RoadRepo;
import ru.serjir.task.repository.StationRepo;
import ru.serjir.task.service.BuildGraph;
import ru.serjir.task.service.TrainService;


import java.util.List;

@Data
@RestController
public class Controller {


    @Autowired
    StationRepo stationRepo;
    @Autowired
    RoadRepo roadRepo;


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
    //  }toString

    @GetMapping("/stations")
    public List<Station> getStations() {

        List<Station> all = stationRepo.findAll();
        return all;
    }
    @GetMapping("/roads")
    public List<Road> getRoads() {
        return roadRepo.findAll();
    }
    @PostMapping("/stations")
        public Station save(@RequestBody Station station){
            return stationRepo.save(station);
    }
    @PostMapping("/road")

    public Road save(@RequestBody Road roads) {


            return roadRepo.save(roads);
        }

    }
