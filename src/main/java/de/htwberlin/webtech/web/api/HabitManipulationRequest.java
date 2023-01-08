package de.htwberlin.webtech.web.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class HabitManipulationRequest {


    private String name;

    private String personid;

    private Boolean done;

    public HabitManipulationRequest( String name, Boolean done) {
        this.name = name;
        this.done = done;
    }

    public HabitManipulationRequest(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}




