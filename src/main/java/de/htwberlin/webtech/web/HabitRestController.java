package de.htwberlin.webtech.web;

import de.htwberlin.webtech.persistence.HabitEntity;
import de.htwberlin.webtech.persistence.HabitRepository;
import de.htwberlin.webtech.service.HabitService;
import de.htwberlin.webtech.web.api.Habit;
import de.htwberlin.webtech.web.api.HabitManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HabitRestController {

    private final HabitService habitService;


    public HabitRestController(HabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping(path = "api/v1/habits")
    public ResponseEntity<List<Habit>> fetchHabit() {
        return ResponseEntity.ok(habitService.findAll());

    }


    @GetMapping(name = "api/v1/habits/{id}")
    public ResponseEntity<Habit> fetchHabitById(@PathVariable(name = "id") Long id) {
        var habit = habitService.findById(id);
        return habit != null ? ResponseEntity.ok(habit) : ResponseEntity.notFound().build();
        //... noch nicht funktionsfähig
    }

    @PostMapping(path = "api/v1/habits")
    public ResponseEntity<Void> createHabit(@RequestBody HabitManipulationRequest request) throws URISyntaxException {
        var habit = habitService.create(request);
        URI uri = new URI("api/v1/habits/" + habit.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "api/v1/habits/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable Long id, @RequestBody HabitManipulationRequest request) throws URISyntaxException {
        var habit = habitService.update(id, request);
        return habit != null ? ResponseEntity.ok(habit) : ResponseEntity.notFound().build();
    }
    @DeleteMapping(path = "api/v1/habits/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id){
        boolean sucessfull = habitService.deleteById(id);
        return sucessfull? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}