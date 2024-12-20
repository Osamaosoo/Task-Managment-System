/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Task;
import model.TaskManager;
import model.TaskFactory;

public class TaskController {

    private TaskManager taskManager;

    public TaskController(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void addTask(String name, String description) {
        Task task = TaskFactory.createTask(name, description);
        taskManager.addTask(task);
    }

    public void removeTask(Task task) {
        taskManager.removeTask(task);
    }

    public void addTask(String name, String description, String category, String dueDate) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
