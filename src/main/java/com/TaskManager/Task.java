package com.TaskManager;
import java.util.concurrent.atomic.AtomicLong;
import java.time.Instant;


public class Task {

    // private class members

    private static final AtomicLong counter = new AtomicLong(1); // initialize the id incremneter
    private final Instant createdAt; // a timestamp to keep track of when the task was created
    private Instant updatedAt; // gets updated every time the task is updated
    private final long id;
    private Status currentStatus;
    private String name;
    private String description;

    // a function to update time each time the task is updated

    private void markUpdated() {
        this.updatedAt = Instant.now();
    }

    // an enum to keep track of the status
    public enum Status {
        IN_PROGRESS,
        TODO,
        COMPLETED,
        FAILED
    }

    // Task class constructor

    public Task(String name, Status currentStatus, String description) {

      // Auto increment the id
       this.id = counter.getAndIncrement();
       // set time
       this.createdAt = Instant.now();
       this.updatedAt = Instant.now();
       this.name = name;
       this.description = description;
       this.currentStatus = currentStatus;
    }

    // if the user hasn't provided the description

    public Task(String name, Status currentStatus) {

        this(name, currentStatus, "not provided");
    }

    // getters

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }
    public String getDescription() {
        return description;
    }

    //setters

    public void setName(String name) {
       this.name = name;
        markUpdated();
    }

    public void setStatus (Status status) {
        this.currentStatus = status;
        markUpdated();
    }

    public void setDescription(String description) {
        this.description = description;
        markUpdated();
    }
}
