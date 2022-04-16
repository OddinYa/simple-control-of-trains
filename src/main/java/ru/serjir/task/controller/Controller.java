package ru.serjir.task.controller;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.serjir.task.service.BuildGraph;
import ru.serjir.task.service.TrainService;

@Data
@RestController
public class Controller {

    @Autowired
    BuildGraph buildGraph;
    @Autowired
    TrainService trainService;


    @GetMapping("/trains")
    public ResponseEntity getTrain() {
        try {
            return ResponseEntity.ok(trainService.checkCollision());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }

    }

}
