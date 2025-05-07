import java.io.*;
import java.util.*;

public class FileManager {

    private static final String FILE_NAME = "progress.txt";

    public static void saveProgress(Trainee trainee) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(trainee.getName() + "\n");
            for (Task task : trainee.getTasks()) {
                writer.write(task.getTitle() + "," + task.getStatus() + "," + task.getDayOfWeek() + "\n");
            }
            System.out.println("Progress saved to progress.txt");
        } catch (IOException e) {
            System.out.println("Error saving progress: " + e.getMessage());
        }
    }

    public static Trainee loadProgress() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("No existing progress found. A new file has been created.");
                return null;
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
                return null;
            }
        }

        try (Scanner scanner = new Scanner(file)) {
            String name = scanner.nextLine();
            Trainee trainee = new Trainee(name);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()) {
                    String[] taskData = line.split(",");
                    if (taskData.length == 3) {
                        Task task = new Task(taskData[0], taskData[2]);
                        task.setStatus(taskData[1]);
                        trainee.addTask(task);
                    }
                }
            }
            return trainee;

        } catch (FileNotFoundException e) {
            System.out.println("Error loading progress: " + e.getMessage());
            return null;
        }
    }
}
