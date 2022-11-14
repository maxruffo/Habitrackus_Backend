package de.htwberlin.webtech.persistence;

import javax.persistence.*;

@Entity(name = "habits")
public class HabitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "done")
    private Boolean done;

    public HabitEntity(String name, Boolean done) {
        this.name = name;
        this.done = done;
    }

    protected HabitEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
