import java.util.List;

public interface TaskRepository {
    void save(List<Task> tasks);
    List<Task> load();
}