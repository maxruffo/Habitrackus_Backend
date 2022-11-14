package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.HabitEntity;
import de.htwberlin.webtech.persistence.HabitRepository;
import de.htwberlin.webtech.web.api.Habit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HabitService {

    private final HabitRepository habitRepository;
    public HabitService(HabitRepository habitRepository){
        this.habitRepository = habitRepository;
    }

    public List<Habit> findAll(){
        List<HabitEntity> habits = habitRepository.findAll();
        return habits.stream()
                .map(habitEntity -> new Habit(
                        habitEntity.getId(),
                        habitEntity.getName(),
                        habitEntity.getDone()
                ))
                .collect(Collectors.toList());
    }

}
