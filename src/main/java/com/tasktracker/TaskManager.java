package com.tasktracker;
import java.util.HashMap;
import java.util.ArrayList;

public class TaskManager {

    // HashMap that stores all active tasks in the application
    // Each task is stored using its ID as the key for fast lookup

    private final HashMap<Long, Task> tasks;
    private final FileHandler filehandler;

    // The FileHandler is passed from the main application
    // When TaskManager is created, it initializes the HashMap that manages all tasks

    public TaskManager(FileHandler filehandler) {
        this.filehandler = filehandler;
        this.tasks = new HashMap<>();
    }

    // If the user provides a JSON file, deserialize it
    // Then pass the loaded tasks to the TaskManager

    public TaskManager(FileHandler filehandler, HashMap<Long, Task> tasks) {
        this.filehandler = filehandler;
        this.tasks = tasks;
    }

    // Helper method that checks if a task exists by id lookup

    public boolean validateId (long id) {
        return tasks.containsKey(id);
    }

    // Creates a new task and adds it to the HashMap
    // Then file operations is managed by FileHandler

    public boolean addTask(String name,Task.Status status,String desc) {
        // Create a new Task instance

        Task task = new Task(name,status,desc);

        // Add the task to the HashMap using its ID as the key

        long id = task.getId();
        tasks.put(id,task);

        return filehandler.saveToFile(tasks);
    }

    public boolean updateName(Task task, String name) {

        task.setName(name);

        return filehandler.saveToFile(tasks);
    }

    public boolean updateDescription (Task task, String desc) {

        task.setDescription(desc);

        return filehandler.saveToFile(tasks);
    }

    public boolean updateStatus(Task task, Task.Status status) {

        task.setStatus(status);

        return filehandler.saveToFile(tasks);
    }

    public boolean deleteTask(Task task) {

        // Retrieve the task ID and remove it from the HashMap

        Long id = task.getId();
        tasks.remove(id);

        return filehandler.saveToFile(tasks);
    }

    // searches for a task by its id then return the task

    public Task getTaskById (long id) {
        return tasks.get(id);
    }


    // Returns a list of all the tasks

    public HashMap<Long,Task> getTasks() {
        return tasks;
    }

    // Returns a list of tasks filtered by their status

    public ArrayList<Task> getTasksByStatus(Task.Status status) {

        ArrayList<Task> tasksbystatus = new ArrayList<Task>();

        for(Task task : tasks.values()) {

            if (task.getCurrentStatus() == status) {
                tasksbystatus.add(task);
            }
        }

        return tasksbystatus;
    }


    // lists all the tasks in the file

    public void listTasks(HashMap<Long, Task> tasks) {
        for (Task task : tasks.values()) {
            System.out.println(task);
        }
    }

    // After retrieving all the tasks with that particular status print them all out

    public void listTasksByStatus(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}


