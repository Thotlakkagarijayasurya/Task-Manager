import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager( new FileTaskRepository());
        manager.loadTasksFromFile("tasks.ser");
        while (true) {
            System.out.println("\n========= Task Manager =========");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Filter by Priority");
            System.out.println("6. Filter by Status");
            System.out.println("7. Sort by Deadline");
            System.out.println("8. Sort by Priority");
            System.out.println("9. Sort by Status");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Priority (HIGH/MEDIUM/LOW): ");
                    TaskPriority priority = TaskPriority.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("Status (PENDING/ACTIVE/COMPLETED/CLOSED): ");
                    TaskStatus status = TaskStatus.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("Start Time (yyyy-MM-ddTHH:mm): ");
                    String startTime = scanner.nextLine();
                    System.out.print("End Time (yyyy-MM-ddTHH:mm): ");
                    String endTime = scanner.nextLine();

                    boolean added = manager.addTask(title, description, priority, status, startTime, endTime);
                    manager.saveTasksToFile();
                    System.out.println(added ? "Task added." : "Duplicate task. Not added.");
                }
                case 2 -> {
                    System.out.print("Task ID to update: ");
                    int id = scanner.nextInt(); scanner.nextLine();
                    System.out.print("New Title: ");
                    String title = scanner.nextLine();
                    System.out.print("New Description: ");
                    String description = scanner.nextLine();
                    System.out.print("New Priority (HIGH/MEDIUM/LOW): ");
                    TaskPriority priority = TaskPriority.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("New Status (PENDING/ACTIVE/COMPLETED/CLOSED): ");
                    TaskStatus status = TaskStatus.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("New Start Time (yyyy-MM-ddTHH:mm): ");
                    String startTime = scanner.nextLine();
                    System.out.print("New End Time (yyyy-MM-ddTHH:mm): ");
                    String endTime = scanner.nextLine();

                    boolean updated = manager.updateTask(id, title, description, priority, status, startTime, endTime);
                    manager.saveTasksToFile();
                    System.out.println(updated ? "Task updated." : "Task not found.");
                }
                case 3 -> {
                    System.out.print("Task ID to delete: ");
                    int id = scanner.nextInt(); scanner.nextLine();
                    boolean deleted = manager.deleteTask(id);
                    manager.saveTasksToFile();
                    System.out.println(deleted ? "Task deleted." : "Task not found.");
                }
                case 4 -> manager.fetchTasks();
                case 5 -> {
                    System.out.print("Priority (HIGH/MEDIUM/LOW): ");
                    TaskPriority priority = TaskPriority.valueOf(scanner.nextLine().toUpperCase());
                    manager.filterByPriority(priority);
                }
                case 6 -> {
                    System.out.print("Status (PENDING/ACTIVE/COMPLETED/CLOSED): ");
                    TaskStatus status = TaskStatus.valueOf(scanner.nextLine().toUpperCase());
                    manager.filterByStatus(status);
                }
                case 7 -> manager.sortByDeadline();
                case 8 -> manager.sortByPriority();
                case 9 -> manager.sortByStatus();
                case 0 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
