package com.tasktracker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        // input

        Scanner input = new Scanner(System.in);

        // main loop

        while (true) {

          // Main menu

            UI.header("Task Manager");

            // Prompt user to create a new JSON file to add tasks to or modify an existing file

            UI.subHeader("Main Menu");

            UI.option("1. Create a new JSON file");
            UI.option("2. Load an existing JSON file");
            UI.option("0. Exit");
            UI.divider();


          switch (input.nextInt()) {
              case 1:{

                  //consume the leftover newline character
                  input.nextLine();

                  // prompt the user to name the file
                  UI.prompt("Enter JSON file name (without extension)");

                  String filename = input.nextLine();
                // create the file using FileHandler
                FileHandler filehandler = new FileHandler(filename);
                // instatiate the taskmanager
                  TaskManager taskmanager = new TaskManager(filehandler);
                // let user know it got created

                  UI.ok("File '" + filename + ".json' created successfully");

                  // call the task menu function
                  taskMenu(input,taskmanager);
                  break;
              }
              case 2: {
                  //consume the leftover newline character
                  input.nextLine();

                  // a boolean to store the result of the validatefile function
                  boolean isvalid = false;

                  // bringing the two variables to the scope

                  FileHandler filehandler;
                  TaskManager taskmanager;

                  do {
                      // user inputs file
                      UI.prompt("Enter JSON file name (without extension)");

                      String filename = input.nextLine();

                      // validate file is in the directory if yes -> call task menu
                      filehandler = new FileHandler(filename);

                      // instatiate the taskmanager
                      taskmanager = new TaskManager(filehandler);

                      isvalid = filehandler.validateFile();

                      if (isvalid) {
                          UI.ok("File found " + "\n" + "loading tasks...");
                      } else {
                          UI.error("File not found. Please try again.");
                      }
                  }
                  while (!isvalid);

                  HashMap<Long, Task> loadedtasks = filehandler.loadFromFile();

                  // construct a task manager

                  taskmanager = new TaskManager(filehandler, loadedtasks);

                  // call the task menu function

                  taskMenu(input, taskmanager);

                  break;
              }
              case 0:
                  UI.what("Goodbye!");
                  System.out.println(); // print a new line
                  return;
          }
        }
    }

    // function for managing the task menu

  public static void taskMenu(Scanner input,TaskManager taskmanager) {

        // prommpt user to choose
    while(true) {

        UI.subHeader("Task Menu");
        UI.option("1. Create a new task");
        UI.option("2. Update an existing task");
        UI.option("3. Delete a task");
        UI.option("4. List all tasks");
        UI.option("5. Filter tasks by status");
        UI.option("0. Back to main menu");
        UI.divider();

        switch (input.nextInt()) {

            case 1: {
                //consume the leftover newline character
                input.nextLine();

                // prompt user
                UI.subHeader("Create New Task");

                UI.prompt("Task name");
                String name = input.nextLine();

                UI.prompt("Description (press enter to skip)");

                String desc = input.nextLine();

                // Decription field is optional

                if(desc.trim().isEmpty()) {
                    desc = "no description provided";
                }

                UI.what("Enter status (TODO / IN_PROGRESS / COMPLETED / FAILED)");

                // validate input and get the status of task

                Task.Status status = promptStatus(input);

                // Create a new task

                if (taskmanager.addTask(name,status,desc)) {
                    UI.ok("Task " +name +" Was Created Successfully");
                }
                else {
                    UI.error("Error: Failure While Creating Task");
                    System.exit(1); // failure status TODO:add a way to sorta log the issue,after you add gui and other stuff
                }

                break;
            }
            case 2:
            {

                //consume the leftover newline character
                input.nextLine();

                UI.subHeader("Update Task");
                // validate input and get the task

                Task task = promptId(input,taskmanager);
                // prompt the user to update

                    UI.what("What would you like to update?");

                    UI.option("  name / description / status");
                    UI.prompt("Field");

                    switch (input.nextLine().toLowerCase().trim()) {

                        case "name": {
                            UI.prompt("New name");

                            if(taskmanager.updateName(task,input.nextLine())) {
                                UI.ok("Name updated! ");
                            }
                            else {
                                UI.error("Error: Failed to update name");
                            }

                            break;
                        }

                        case "description": {
                            UI.prompt("New description");

                            if (taskmanager.updateDescription(task,input.nextLine())) {
                                UI.ok("Description updated!");
                            }
                            else {
                                UI.error("Error: Failed to update description.");
                            }

                            break;
                        }

                        case "status": {

                            // validate input and get the Task Status
                            UI.what("Enter new status (TODO / IN_PROGRESS / COMPLETED / FAILED)");
                            Task.Status status = promptStatus(input);

                            // update the status of the taska

                            if (taskmanager.updateStatus(task,status)) {
                                UI.ok("Status Updated! ");
                            }
                            else {
                                UI.error("Error: Failed to update status.");
                            }
                            break;
                        }
                        default: {
                            UI.warning("Invalid field. Nothing was updated.");
                            break;
                        }
                    }
                break;
            }
            case 3:
            {
                //consume the leftover newline character
                input.nextLine();

                UI.subHeader("Delete Task");

                // validate input and get the task

                Task task = promptId(input,taskmanager);

                // delete task and print completiton

                if (taskmanager.deleteTask(task)) {
                    UI.ok("Task Deleted! ");
                }
                else {
                    UI.error("Error: Failed to delete Task.");
                }
                break;
            }
            case 4:
            {
                //consume the leftover newline character
                input.nextLine();

                // list all tasks

                UI.subHeader("List all tasks");

                taskmanager.listTasks(taskmanager.getTasks());
                break;
            }
            case 5:
            {
                //consume the leftover newline character
                input.nextLine();

                UI.subHeader("Filter Tasks by status");

                // prompt user to provide the status

                UI.prompt("Enter a Valid Status");

                Task.Status status = promptStatus(input);

                // search for every task with this status

                ArrayList<Task> taskstoprint = taskmanager.getTasksByStatus(status);

                if(!taskstoprint.isEmpty()) {
                    taskmanager.listTasksByStatus(taskstoprint);
                }

                else {
                    UI.error("No tasks found with that status ");
                }
                break;
            }
            case 0:
                UI.what("Returning to main menu...");
                return;
        }
    }
  }

    /** Helper functions for input validation
     **/


    // A function that validates status input and then return the status

    public static Task.Status promptStatus(Scanner input) {

        // create an instance of the task status

        Task.Status status = null;

        // keep re-prompting the user untill a valid input is there or timeout is reached

        do {
            try {
                // An input formatted status that then gets converted into The Status Enum type

                String strstatus = input.nextLine();

                // convert the user's input into one of the enum values in the task class
                status = Task.Status.valueOf(strstatus.toUpperCase().trim());
            }

            catch (IllegalArgumentException e) {
                UI.error("Invalid status. Please enter: TODO, IN_PROGRESS, COMPLETED or FAILED");
                UI.prompt("Try again");
            }
        }
        while(status == null);

        return status;
    }

    // A function that validates Id and then return the task with that id

    public static Task promptId(Scanner input,TaskManager taskmanager) {

        // intialize the id variable

        long id = -1;

        // a boolean to store the result of the validate id function

        boolean isidvalid = false;

        // a do while loop to validate user's input

        do {
            UI.prompt("Enter Task id");

            id = input.nextLong();

            isidvalid = taskmanager.validateId(id);

            if (isidvalid) {
                UI.ok("Task Found!");
            }
            else {
                UI.error("No task found with that id. Please try again.");
            }

        }
        while(!isidvalid);

        // consume the leftover newline

        input.nextLine();

        // return the task after validation

        return taskmanager.getTaskById(id);
    }

}


