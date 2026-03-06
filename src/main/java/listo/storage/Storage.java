package listo.storage;

import listo.task.Task;
import listo.task.Todo;
import listo.task.Deadline;
import listo.task.Event;
import listo.task.TaskList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a new Storage instance.
     *
     * @param filePath The file path where tasks will be saved and loaded.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an I/O error occurs.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // Create directory if it doesn't exist
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        // Create file if it doesn't exist
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");

            try {
                Task task = null;
                switch (parts[0]) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                    default:
                        System.out.println("Skipping invalid task type: " + parts[0]);
                        break;
                }

                if (task != null) {
                    if (parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            } catch (Exception e) {
                System.out.println("Warning: Skipping corrupted line in save file: " + line);
            }
        }
        scanner.close();
        return tasks;
    }

    /**
     * Saves the current list of tasks to the file.
     *
     * @param tasks The listo.task.TaskList containing tasks to save.
     */
    public void save(TaskList tasks) {
        assert tasks != null : "TaskList to save cannot be null";
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                writer.write(tasks.getTask(i).toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}