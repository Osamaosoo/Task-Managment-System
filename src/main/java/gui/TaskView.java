package gui;
// Model-View-Controller (MVC) Pattern 

import model.Task;
import model.TaskManager;
import java.util.List;

public class TaskView {

    private TaskManager taskManager;

    public TaskView(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void displayTasks() {
        List<Task> tasks = taskManager.getTasks();
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
