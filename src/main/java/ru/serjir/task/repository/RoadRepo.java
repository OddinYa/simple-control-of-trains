package ru.serjir.task.repository;

import org.springframework.data.repository.CrudRepository;
import ru.serjir.task.entity.Road;


public interface RoadRepo extends CrudRepository<Road, Integer> {

}
