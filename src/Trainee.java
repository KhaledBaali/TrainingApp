import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Trainee implements Serializable {
    private String name;
    private List<Task> tasks;

    public Trainee(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void updateTaskStatus(String taskTitle, String status) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(taskTitle)) {
                task.setStatus(status);
                System.out.println("Task status updated.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("You have no tasks yet!");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    @Override
    public String toString() {
        return "Trainee: " + name;
    }
}
