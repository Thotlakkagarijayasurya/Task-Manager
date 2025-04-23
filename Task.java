import java.io.Serializable;
import java.time.LocalDateTime;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String title;
    String description;
    TaskPriority priority;
    TaskStatus status;
    LocalDateTime startTime;
    LocalDateTime endTime;
    private static int count=0;
    public Task(String title, String description, TaskPriority priority,
                TaskStatus status, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = ++count;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static void setCount(int maxId) {
        count = maxId;
    }

    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return id + "\t" + title + "\t" + description + "\t" + priority + "\t" + status + "\t" + startTime + "\t" + endTime;
    }
}
