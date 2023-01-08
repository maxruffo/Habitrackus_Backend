package de.htwberlin.webtech.web;

import de.htwberlin.webtech.persistence.HabitEntity;
import de.htwberlin.webtech.persistence.HabitRepository;
import de.htwberlin.webtech.service.HabitService;
import de.htwberlin.webtech.web.api.Habit;
import de.htwberlin.webtech.web.api.HabitManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @GetMapping(path = "api/v1/habits/{id}")
    public ResponseEntity<Habit> fetchHabitById(@PathVariable Long id) {
        var habit = habitService.findById(id);
        return habit != null? ResponseEntity.ok(habit) : ResponseEntity.notFound().build();

    }

    @PostMapping(path = "api/v1/habits")
    public ResponseEntity<Void> createHabit(@RequestBody HabitManipulationRequest request) throws URISyntaxException {
        var valid = validate(request);
        if(valid){
            var habit = habitService.create(request);
            URI uri = new URI("api/v1/habits/" + habit.getId());
            return ResponseEntity.created(uri).build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "api/v1/habits/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable Long id, @RequestBody HabitManipulationRequest request) throws URISyntaxException {
        var habit = habitService.update(id, request);
        return habit != null ? ResponseEntity.ok(habit) : ResponseEntity.notFound().build();
    }
    @DeleteMapping(path = "api/v1/habits/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id){
        boolean successful = habitService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    private boolean validate(HabitManipulationRequest request){
        return request.getName() != null
                && !request.getName().isBlank();
    }
}
