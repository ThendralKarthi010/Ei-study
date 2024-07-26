import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Task class representing a task with attributes description, start time, end time, and priority level
class Task {
    private String description;
    private String startTime; // Format HH:mm
    private String endTime; // Format HH:mm
    private int priority;

    public Task(String description, String startTime, String endTime, int priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", priority=" + priority +
                '}';
    }
}

// TaskFactory class to create Task objects
class TaskFactory {
    public Task createTask(String description, String startTime, String endTime, int priority) {
        return new Task(description, startTime, endTime, priority);
    }
}

// Observer pattern interface for task conflict notifications
interface TaskObserver {
    void notifyConflict(Task task);
}

// Singleton ScheduleManager class that manages all tasks
class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;
    private List<TaskObserver> observers;

    private ScheduleManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }

    public void addTask(Task task) {
        if (isConflicting(task)) {
            notifyObservers(task);
        } else {
            tasks.add(task);
            tasks.sort((t1, t2) -> t1.getStartTime().compareTo(t2.getStartTime()));
            System.out.println("Task added successfully.");
        }
    }

    public void removeTask(String description) {
        Task taskToRemove = null;
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                taskToRemove = task;
                break;
            }
        }
        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            System.out.println("Task removed successfully.");
        } else {
            System.out.println("Task not found.");
        }
    }

    public void viewTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void viewTasksByPriority(int priority) {
        for (Task task : tasks) {
            if (task.getPriority() == priority) {
                System.out.println(task);
            }
        }
    }

    private boolean isConflicting(Task newTask) {
        for (Task task : tasks) {
            if (newTask.getStartTime().compareTo(task.getEndTime()) < 0 && newTask.getEndTime().compareTo(task.getStartTime()) > 0) {
                return true;
            }
        }
        return false;
    }

    private void notifyObservers(Task task) {
        for (TaskObserver observer : observers) {
            observer.notifyConflict(task);
        }
    }
}

// Implementation of TaskObserver to handle conflict notifications
class ConflictNotifier implements TaskObserver {
    @Override
    public void notifyConflict(Task task) {
        System.out.println("Conflict detected for task: " + task);
    }
}

// Main application class
public class AstronautDailyScheduleOrganizer {
    public static void main(String[] args) {
        ScheduleManager scheduleManager = ScheduleManager.getInstance();
        TaskFactory taskFactory = new TaskFactory();
        ConflictNotifier conflictNotifier = new ConflictNotifier();
        scheduleManager.addObserver(conflictNotifier);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View All Tasks");
            System.out.println("4. View Tasks by Priority");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter start time (HH:mm): ");
                    String startTime = scanner.nextLine();
                    System.out.print("Enter end time (HH:mm): ");
                    String endTime = scanner.nextLine();
                    System.out.print("Enter priority level (1-5): ");
                    int priority = scanner.nextInt();
                    Task task = taskFactory.createTask(description, startTime, endTime, priority);
                    scheduleManager.addTask(task);
                    break;
                case 2:
                    System.out.print("Enter description of task to remove: ");
                    String descToRemove = scanner.nextLine();
                    scheduleManager.removeTask(descToRemove);
                    break;
                case 3:
                    scheduleManager.viewTasks();
                    break;
                case 4:
                    System.out.print("Enter priority level to view (1-5): ");
                    int priorityLevel = scanner.nextInt();
                    scheduleManager.viewTasksByPriority(priorityLevel);
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}