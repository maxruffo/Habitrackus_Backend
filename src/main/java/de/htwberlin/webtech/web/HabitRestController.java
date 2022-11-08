package de.htwberlin.webtech.web;

import de.htwberlin.webtech.web.api.Habit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HabitRestController {

    private List<Habit> habits;

    public HabitRestController(){
        habits = new ArrayList<>();
        habits.add(new Habit(1L,"Wake Up at 8 am"));
        habits.add(new Habit(2L,"Read 10 Pages of a Book"));
    }
    @GetMapping(path ="api/v1/habits")
    public ResponseEntity<List<Habit>> fetchHabit(){
        return ResponseEntity.ok(habits);

    }

}
