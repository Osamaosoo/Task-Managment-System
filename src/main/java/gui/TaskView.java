package gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author TOP
 */
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
