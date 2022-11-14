package de.htwberlin.webtech.web;

import de.htwberlin.webtech.persistence.HabitEntity;
import de.htwberlin.webtech.persistence.HabitRepository;
import de.htwberlin.webtech.service.HabitService;
import de.htwberlin.webtech.web.api.Habit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HabitRestController {

    private final HabitService habitService;
    

    public HabitRestController(HabitService habitService){
        this.habitService = habitService;
    }
    @GetMapping(path ="api/v1/habits")
    public ResponseEntity<List<Habit>> fetchHabit(){
        return ResponseEntity.ok(habitService.findAll());

    }

}
