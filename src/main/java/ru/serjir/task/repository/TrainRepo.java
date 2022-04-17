package ru.serjir.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.serjir.task.entity.Train;

@Repository
public interface TrainRepo extends JpaRepository<Train,Integer> {
}
