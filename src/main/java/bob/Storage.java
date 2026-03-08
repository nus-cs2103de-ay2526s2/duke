package bob;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Bob storage class code that handles saving and loading tasks
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for storage
     * @param filePath the name of the filepath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void load(ArrayList<Task> tasks){
        try {
            assert tasks != null;
            // Handle case where data folder and file doesn't exist
            File folder = new File("data");
            if (folder.exists() == false) {
                folder.mkdir();
            }
            File file = new File(filePath);
            if (file.exists() == false) {
                file.createNewFile();
                return;
            }
            // Read the file using BufferedReader and handle each case with helper methods
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[T]")) {
                    loadToDo(tasks, line);
                } else if (line.startsWith("[D]")) {
                    loadDeadline(tasks, line);
                } else if (line.startsWith("[E]")) {
                    loadEvent(tasks, line);
                }
            }
            reader.close();
        } catch (IOException e){
            System.out.println("Error loading: " + e.getMessage());
        }
    }

    /**
     * load to do command helper method
     * @param tasks the task list
     * @param line the whole line of text input
     */
    private void loadToDo(ArrayList<Task> tasks, String line) {
        // Check if task is done
        boolean isDone = line.contains("[X]");
        String description = line.substring(6);

        Task task = new ToDo(description);
        if (isDone){
            task.markAsDone();
        }
        tasks.add(task);
    }

    /**
     * loaddeadline helper method
     * @param tasks the task list
     * @param line the whole line of text input
     */
    private void loadDeadline(ArrayList<Task> tasks, String line){
        boolean isDone = line.contains("[X]");
        int byIndex = line.indexOf(" (by: ");
        String description = line.substring(6, byIndex);
        String dateString = line.substring(byIndex + 6, line.length() - 1);

        // Convert display format back to yyyy-mm-dd using helper method
        LocalDate date = convertToLocalDate(dateString);

        Task task = new Deadline(description, date.toString());
        if (isDone){
            task.markAsDone();
        }
        tasks.add(task);
    }

    /**
     * loadevent helper method
     * @param tasks the task list
     * @param line the whole line of input text
     */
    private void loadEvent(ArrayList<Task> tasks, String line) {
        boolean isDone = line.contains("[X]");
        int fromIndex = line.indexOf(" (from: ");
        String description = line.substring(6, fromIndex);
        int toIndex = line.indexOf(" to: ");
        String startString = line.substring(fromIndex + 8, toIndex);
        String endString = line.substring(toIndex + 5, line.length() - 1);

        // Convert display format back to yyyy-mm-dd with helper method
        LocalDate startDate = convertToLocalDate(startString);
        LocalDate endDate = convertToLocalDate(endString);

        Task task = new Event(description, startDate.toString(), endDate.toString());
        if (isDone){
            task.markAsDone();
        }
        tasks.add(task);
    }

    /**
     * Save method to save tasks
     * Saves current task list into the save file
     * @param tasks the list of tasks to save
     */
    public void save(ArrayList<Task> tasks) {
        try {
            assert tasks != null;
            // Create a filewriter linked to save file
            FileWriter writer = new FileWriter(filePath);
            // Write each task into file line by line
            for (int i = 0; i < tasks.size(); i ++){
                writer.write(tasks.get(i).toString() + "\n");
            }
            writer.close();
        } catch (IOException e){
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    /**
     * Helper method to covert date from
     * display format to LocalDate
     * @param dateString the date in string
     */
    private LocalDate convertToLocalDate(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return LocalDate.parse(dateString, formatter);
        }
        catch (Exception e){
            return LocalDate.parse(dateString);
        }
    }

    /**
     * Return motivational quote from cheer file
     * Creates the data folder if missing
     * @return a random quote string
     */
    public String getRandomCheerQuote() {
        try {
            File folder = new File("data");
            if (!folder.exists()) {
                folder.mkdir();
            }

            File file = new File("data/cheer.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> quotes = new ArrayList<>();
            // Read file line by line until it is null
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() == false) {
                    quotes.add(line.trim());
                }
            }
            reader.close();

            if (quotes.isEmpty()) {
                return "No quotes found. Add them in data/cheer.txt";
            }
            // Choose random quote from the list
            Random random = new Random();
            return quotes.get(random.nextInt(quotes.size()));

        } catch (IOException e) {
            return "Unable to read cheer quotes.";
        }
        }
    }
