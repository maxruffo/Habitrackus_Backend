package de.htwberlin.webtech.persistence;

import de.htwberlin.webtech.web.api.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<HabitEntity, Long> {
    List<HabitEntity> findAll();
}
