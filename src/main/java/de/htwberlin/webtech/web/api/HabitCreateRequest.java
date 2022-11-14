package de.htwberlin.webtech.web.api;

public class HabitCreateRequest {
    private String name;
    private Boolean done;

    public HabitCreateRequest( String name, Boolean done) {
        this.name = name;
        this.done = done;
    }

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




