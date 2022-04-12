package ru.serjir.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.serjir.task.entity.Road;

@Repository
public interface RoadRepo extends JpaRepository<Road, Integer> {

}
