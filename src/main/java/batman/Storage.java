package batman;

import task.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that handles the storage of list.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Save the current list to the .txt file.
     * @param tasks The current list.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();

            // Create directory if it doesn't exist
            if (directory != null && !directory.exists()) {
                directory.mkdirs();
            }

            try (FileWriter writer = new FileWriter(filePath)) {
                for (Task task : tasks) {
                    writer.write(task.toFileString() + System.lineSeparator());
                }
            }
        }
        catch (IOException e) {
            System.out.println("     Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Load the list stored in the .txt file.
     * @return The list in the .txt file. If there is no such file, return empty list.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // If file doesn't exist, return empty list
        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Parse the line (e.g., "T | 1 | read book")
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        catch (Exception e) {
            System.out.println("     Error loading file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Convert the string in the .txt file into a Task object.
     * @param line A line in the file.
     * @return A Task object in the list. If the string is corrupted, return null.
     */
    private Task parseTask(String line) {
        // Example line: D | 0 | return book | June 6th
        String[] parts = line.split(" \\| ");

        // Stretch goal: Handle corrupted data
        if (parts.length < 3) return null;

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;

        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length >= 4) {
                    task = new Deadline(description, parts[3]);
                }
                break;
            case "E":
                if (parts.length >= 5) {
                    task = new Event(description, parts[3], parts[4]);
                }
                break;
        }

        if (task != null && isDone) {
            task.setAsDone();
        }
        return task;
    }
}