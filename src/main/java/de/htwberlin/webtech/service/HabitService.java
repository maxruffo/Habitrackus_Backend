package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.HabitEntity;
import de.htwberlin.webtech.persistence.HabitRepository;
import de.htwberlin.webtech.web.api.Habit;
import de.htwberlin.webtech.web.api.HabitManipulationRequest;
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
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Habit findById(Long id){
        var habitEntity = habitRepository.findById(id);
        return habitEntity.map(this::transformEntity).orElse(null);
    }
    public Habit create(HabitManipulationRequest request){
        var habitEntity = new HabitEntity(request.getName(),request.isDone());
        habitEntity = habitRepository.save(habitEntity);
        return transformEntity(habitEntity);
    }

    public Habit update(Long id,HabitManipulationRequest request){
        var habitEntityOptional = habitRepository.findById(id);
        if(habitEntityOptional.isEmpty()){
            return null;
        }
        var habitEntity = habitEntityOptional.get();
        habitEntity.setName(request.getName());
        habitEntity.setDone(request.isDone());
        habitEntity = habitRepository.save(habitEntity);

        return transformEntity(habitEntity);
    }

    public boolean deleteById(Long id){
        if(!habitRepository.existsById(id)){
            return false;
        }
        habitRepository.deleteById(id);
        return true;
    }

    private Habit transformEntity(HabitEntity habitEntity){
        return new Habit(
                habitEntity.getId(),
                habitEntity.getName(),
                habitEntity.getDone()
        );


    }

}
