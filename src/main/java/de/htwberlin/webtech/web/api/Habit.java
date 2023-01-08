package de.htwberlin.webtech.web.api;

public class Habit {

    private long id;
    private String name;
    private Boolean done;

    public Habit(long id, String name, Boolean done) {
        this.id = id;
        this.name = name;
        this.done = done;
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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



