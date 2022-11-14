package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.HabitEntity;
import de.htwberlin.webtech.persistence.HabitRepository;
import de.htwberlin.webtech.web.api.Habit;
import de.htwberlin.webtech.web.api.HabitCreateRequest;
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
                .map(this::transformHabit)
                .collect(Collectors.toList());
    }

    public Habit create(HabitCreateRequest request){
        var habitEntity = new HabitEntity(request.getName(),request.isDone());
        habitEntity = habitRepository.save(habitEntity);
        return transformHabit(habitEntity);
    }

    private Habit transformHabit(HabitEntity habitEntity){
        return new Habit(
                habitEntity.getId(),
                habitEntity.getName(),
                habitEntity.getDone()
        );


    }

}
