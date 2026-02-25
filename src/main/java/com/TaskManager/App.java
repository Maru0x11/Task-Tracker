package com.TaskManager;
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

          System.out.println("-- Task Manager -- ");

          // Prompt user to create a new JSON file to add tasks to or modify an existing file

          System.out.println("Choose What to do ? ");

          System.out.println("1. Create a new JSON file (To add Tasks to it)");

          System.out.println("2. modify an existing JSON file "); // if user chooses and input a wrong name create a new json file with that namez

          System.out.println("0. Exit ");

          switch (input.nextInt()) {

              case 1:
                // prompt the user to name the file
                // create the file using FileHandler
                // let user know it got created
                // call the task menu function
              case 2:

                  // user inputs file
                  // validate file is in the directory if yes -> call task menu
                  // let user know everything is ok
                  // if no -> warn them file not found , do you wanna create a file name with this name (y/n)
                  // yes -> create , no -> prompt for filename
                  // call the task menu function
              case 0:
                  return;
          }
        }
    }

    // function for managing the task menu

  public static void taskMenu(Scanner input) {

        // prommpt user to choose
    while(true) {


        System.out.println("-- Choose What to do ! -- ");

        System.out.println("1. Create a new task (with a description (optional) ) ");

        System.out.println("2. update an existing task ");

        System.out.println("3. Delete an existing task ");

        System.out.println("4. Change Task's Status ");

        System.out.println("5. List all tasks ");

        System.out.println("6. Filter tasks by Status");

        System.out.println("0. Exit ");

        switch (input.nextInt()) {

            case 1:
                // prompt user
                // task name
                // task desc
                // save task to the file
                // print completion

            case 2:
                //prompt user
                // task name
                // if the name is not in the file
                // then print not found and reprompt the user to either create a new task with this name
                // or input the right task
                // prompt the user to update name and desc
                // update and save to file
                // print completion

            case 3:
                //prompt user
                // task name
                // if the name is not in the file tell
                // then print not found and reprompt the user to provide the correct name or do nothing
                // delete and und update file
                // print completion

            case 4:
                //prompt user
                // task name
                // if the name is not in the file
                // then print not found and reprompt the user to either provide the correct name or do nothing
                // prompt user to whicch status they want to update the task to
                // update and save to file
                // print completion

            case 5:
                //just list all tasks
            case 6:
                // prompt user to provide the status
                // search for every task with this status

            case 0:
                return;
        }
    }
  }

}


