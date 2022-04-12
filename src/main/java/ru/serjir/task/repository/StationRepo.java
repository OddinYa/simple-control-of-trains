package ru.serjir.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import ru.serjir.task.entity.Station;

@Repository
public interface StationRepo extends JpaRepository<Station,Integer> {


}
