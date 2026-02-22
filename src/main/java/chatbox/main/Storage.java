package chatbox.main;

import chatbox.main.tasks.Deadline;
import chatbox.main.tasks.Event;
import chatbox.main.tasks.Task;
import chatbox.main.tasks.ToDo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

/**
 * Handles loading and saving tasks to the file system.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath; //Constructor
    }
    /**
     * Save the tasks accordingly
     */
    public void save(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            } // Create directory
            file.createNewFile();
            DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                String line = "";
                String status = task.isDone() ? "Done" : "Not done";

                if (task instanceof ToDo) {
                    // Use .getDescription() instead of .description
                    line = "Todo | " + status + " | " + task.getDescription();
                }
                else if (task instanceof Deadline) {
                    Deadline d = (Deadline) task;
                    String strictDate = d.getBy().format(saveFormat);
                    line = "Deadline | " + status + " | " + d.getDescription() + " | " + strictDate;
                }
                else if (task instanceof Event) {
                    Event e = (Event) task;
                    String fromDate = e.getFrom().format(saveFormat);
                    String toDate = e.getTo().format(saveFormat);
                    line = "Event | " + status + " | " + e.getDescription() + " | " + fromDate + " | " + toDate;
                }
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("There is a problem saving the file :( " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        // If file doesn't exist, just return an empty list
        if (!file.exists()) {
            return loadedTasks;
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");

                // Parse the parts
                String type = parts[0];
                String status = parts[1];
                String description = parts[2];

                Task task = null;
                switch (type) {
                case "Todo":
                    task = new ToDo(description);
                    break;
                case "Deadline":
                    task = new Deadline(description, parts[3]);
                    break;
                case "Event":
                    task = new Event(description, parts[3], parts[4]);
                    break;
                default:
                    System.out.println("Unknown task type in file: " + type);
                }

                if (task != null) {
                    // Check for "Done" string
                    if (status.equals("Done")) {
                        task.markAsDone();
                    }
                    loadedTasks.add(task);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("OOPS!! Corrupted save file. Starting with empty list.");
        }
        return loadedTasks;
    }
}