package com.tasktracker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


public class FileHandler {

    private final String filepath;
    private final Gson gson;

    public FileHandler(String filepath) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        // make sure to add .json to the file
        this.filepath = filepath.endsWith(".json") ? filepath : filepath + ".json";
    }

    // Checks whether the file exists at the specified path

    public boolean validateFile() {
        return Files.exists(Paths.get(filepath));
    }

    // Saves the HashMap of tasks to a JSON file

    public boolean saveToFile(HashMap<Long,Task> tasks){

       try (final FileWriter fw = new FileWriter(filepath)) {
           // Serialize the HashMap into JSON format
           // Write the JSON output to the file
           gson.toJson(tasks, fw);
           return true;
       }

       catch (IOException e) {
        System.out.println("Error writing to file" + e.getMessage());
       }

       return false;
    }

    // Loads the JSON file from disk
    // Deserializes it into a HashMap of Task objects

    public HashMap<Long,Task> loadFromFile(){

        // // Define the type for deserialization

        Type type = new TypeToken<HashMap<Long,Task>>(){}.getType();

        // Use BufferedReader for efficient file reading

        try (final BufferedReader br = new BufferedReader(new FileReader(this.filepath))) {

            // Deserialize the JSON content into a HashMap of Tasks

            return gson.fromJson(br, type);
        }

        catch (IOException e) {
            System.out.println("Error Reading file" + e.getMessage());
        }

        // when failure return null and handle it in the main menu

         return null;
        }
}


