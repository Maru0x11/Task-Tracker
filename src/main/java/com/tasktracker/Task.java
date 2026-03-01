package com.tasktracker;
import java.util.concurrent.atomic.AtomicLong;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;


public class Task {

    private static final AtomicLong counter = new AtomicLong(1); // Auto-incrementing ID generator
    private final String createdAt; // Timestamp indicating when the task was created
    private String updatedAt; // Timestamp updated whenever the task is modified
    private final long id;
    private Status currentStatus;
    private String name;
    private String description;

    // Updates the timestamp whenever the task is modified

    private void markUpdated() {
        this.updatedAt = Instant.now().toString();
    }


    // overriding the .tostring method to enable the object to be printable
    @Override
    public String toString() {
        return "ID: " + id +
                "\n" +
                " | Name: " + name +
                "\n" +
                " | Status: " + currentStatus +
                "\n" +
                " | Description: " + description +
                "\n" +
                " | Created: " + timeToReadable(Instant.parse(createdAt))+
                "\n" +
                " | Updated: " + timeToReadable(Instant.parse(updatedAt));
    }


    // Enum representing the possible task statuses

    public enum Status {
        IN_PROGRESS,
        TODO,
        COMPLETED,
        FAILED
    }

    // Task class constructor

    // Defualt constructor

    public Task() {
        this.id = counter.getAndIncrement();
        this.createdAt = Instant.now().toString();
        this.updatedAt = Instant.now().toString();
    }

    // full constructor

    public Task(String name, Status currentStatus, String description) {

        // Automatically assign a unique ID
        this.id = counter.getAndIncrement();

        // Set creation and initial update timestamps
        this.createdAt = Instant.now().toString();
        this.updatedAt = Instant.now().toString();

        this.name = name;
        this.description = description;
        this.currentStatus = currentStatus;
    }

    // Constructor used when no description is provided

    public Task(String name, Status currentStatus) {
        this(name, currentStatus, "not provided");
    }


    // Getters

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public String getDescription() {
        return description;
    }

    // Setters (each update also refreshes the updatedAt timestamp)

    public void setName(String name) {
        this.name = name;
        markUpdated();
    }

    public void setStatus(Status status) {
        this.currentStatus = status;
        markUpdated();
    }

    public void setDescription(String description) {
        this.description = description;
        markUpdated();
    }

// Helper functions

// A helper functions to enable switching to a human readable time format while pritniting the task in-app

    public static String timeToReadable(Instant instant) {

        // a predifined style to format the date time based on user's current timezone and locale
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }

}


