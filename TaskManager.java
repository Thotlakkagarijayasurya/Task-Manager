import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskManager{
    private List<Task> tasks;
    public TaskManager(){
        tasks = new ArrayList<>();
    }

    //add task
    public boolean addTask(String title, String description, TaskPriority priority, TaskStatus status, String startTime, String endTime){
        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);

        Task newTask = new Task(title, description, priority, status, start, end);

        if (tasks.contains(newTask)) {
            return false;
        } else {
            tasks.add(newTask);
            return true;
        }
    }

    //display tasks
    public void fetchTasks(){
        System.out.println("Id\tTitle\tDescription\t\tpriority\tstatus\tstartTime\tendTime\n");
        for(Task task: tasks){
            System.out.println(task.toString());
        }
    }

    //update tasks
    public boolean updateTask(int id, String title, String description, TaskPriority priority, TaskStatus status, String startTime, String endTime) {
        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);

        for(Task task: tasks){
            if(task.getId() == id){
                task.setTitle(title);
                task.setDescription(description);
                task.setPriority(priority);
                task.setStatus(status);
                task.setStartTime(start);
                task.setEndTime(end);
                return true;
            }
        }
        return false;
    }

    //delete Tasks
    public boolean deleteTask(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
                return true;
            }
        }
        return false;
    }

    //filter by priority
    public void filterByPriority(TaskPriority priority) {
        System.out.println("Tasks with Priority: " + priority);
        System.out.println("ID\tTitle\tDescription\tPriority\tStatus\tStart\tEnd");

        for (Task task : tasks) {
            if (task.getPriority() == priority) {
                System.out.println(task);
            }
        }
    }

    //filter by status
    public void filterByStatus(TaskStatus status) {
        System.out.println("Tasks with Status: " + status);
        System.out.println("ID\tTitle\tDescription\tPriority\tStatus\tStart\tEnd");

        for (Task task : tasks) {
            if (task.getStatus() == status) {
                System.out.println(task);
            }
        }
    }

    public void sortByDeadline() {
        tasks.sort(Comparator.comparing(Task::getEndTime));
        System.out.println("Tasks sorted by Deadline (endTime):");
        fetchTasks();
    }
    public void sortByStatus() {
        tasks.sort(Comparator.comparing(Task::getStatus));
        System.out.println("Tasks sorted by Status:");
        fetchTasks();
    }
    public void sortByPriority() {
        tasks.sort(Comparator.comparing(Task::getPriority));
        System.out.println("Tasks sorted by Priority:");
        fetchTasks(); // reuse your display method
    }

    public void saveTasksToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void loadTasksFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            tasks = (List<Task>) ois.readObject();

            // Update static id counter
            int maxId = tasks.stream().mapToInt(Task::getId).max().orElse(0);
            Task.setCount(maxId);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}