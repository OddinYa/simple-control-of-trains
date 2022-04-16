package ru.serjir.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.serjir.task.entity.Train;

public interface TrainRepo extends JpaRepository<Train,Integer> {
}
