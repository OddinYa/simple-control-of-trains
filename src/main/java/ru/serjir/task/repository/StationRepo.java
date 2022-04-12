package ru.serjir.task.repository;

import org.springframework.data.repository.CrudRepository;
import ru.serjir.task.entity.Station;


public interface StationRepo extends CrudRepository<Station, Integer> {


}
