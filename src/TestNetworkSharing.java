import java.util.*;

public class TestNetworkSharing {
    static Scanner scanner = new Scanner(System.in);
    static Trainee trainee;

    public static void main(String[] args) {
        trainee = FileManager.loadProgress();

        if (trainee == null) {
            registerTrainee();
        } else {
            System.out.println("Welcome back, " + trainee.getName() + "!");
        }

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View My Training Plan");
            System.out.println("2. Add Task");
            System.out.println("3. Update Task Status");
            System.out.println("4. Save My Progress");
            System.out.println("5. Share Progress Over Network");
            System.out.println("6. Receive Progress From Network");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewTrainingPlan();
                    break;
                case 2:
                    addTaskToTrainee();
                    break;
                case 3:
                    updateTaskStatus();
                    break;
                case 4:
                    FileManager.saveProgress(trainee);
                    break;
                case 5:
                    shareProgressOverNetwork();
                    break;
                case 6:
                    receiveProgressFromNetwork();
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    FileManager.saveProgress(trainee);
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    static void shareProgressOverNetwork() {
        System.out.print("Enter peer IP address: ");
        String peerIP = scanner.nextLine();
        System.out.print("Enter port number: ");
        int port = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        NetworkSharing.sendProgressToPeer(trainee, peerIP, port);
    }

    static void receiveProgressFromNetwork() {
        System.out.print("Enter port number to listen on: ");
        int port = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Trainee receivedTrainee = NetworkSharing.receiveProgressFromPeer(port);
        if (receivedTrainee != null) {
            trainee = receivedTrainee;
            System.out.println("Progress loaded from network!");
        }
    }

    static void registerTrainee() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        trainee = new Trainee(name);
        System.out.println("Welcome " + name + "! You are now registered.");
    }

    static void viewTrainingPlan() {
        System.out.println("\nYour Training Plan:");
        Map<String, List<Task>> tasksByDay = new TreeMap<>();

        for (Task task : trainee.getTasks()) {
            tasksByDay.computeIfAbsent(task.getDayOfWeek(), k -> new ArrayList<>()).add(task);
        }

        for (Map.Entry<String, List<Task>> entry : tasksByDay.entrySet()) {
            System.out.println("\nDay: " + entry.getKey());
            for (Task task : entry.getValue()) {
                System.out.println(task);
            }
        }
    }

    static void addTaskToTrainee() {
        System.out.print("Enter task title: ");
        String taskTitle = scanner.nextLine();

        System.out.println("Select the day of the week:");
        System.out.println("1. Sunday");
        System.out.println("2. Monday");
        System.out.println("3. Tuesday");
        System.out.println("4. Wednesday");
        System.out.println("5. Thursday");
        System.out.println("6. Friday");
        System.out.println("7. Saturday");
        int dayChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String dayOfWeek = "";
        switch (dayChoice) {
            case 1: dayOfWeek = "Sunday"; break;
            case 2: dayOfWeek = "Monday"; break;
            case 3: dayOfWeek = "Tuesday"; break;
            case 4: dayOfWeek = "Wednesday"; break;
            case 5: dayOfWeek = "Thursday"; break;
            case 6: dayOfWeek = "Friday"; break;
            case 7: dayOfWeek = "Saturday"; break;
            default:
                System.out.println("Invalid day selected. Defaulting to Sunday.");
                dayOfWeek = "Sunday";
        }

        trainee.addTask(new Task(taskTitle, dayOfWeek));
        System.out.println("Task added!");
    }

    static void updateTaskStatus() {
        System.out.print("Enter task title: ");
        String taskTitle = scanner.nextLine();
        System.out.print("Enter new status (Completed/Pending/Postponed): ");
        String status = scanner.nextLine();
        trainee.updateTaskStatus(taskTitle, status);
    }
}