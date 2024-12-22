package model;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private static TaskManager instance;
    private List<Task> tasks;
    private List<Observer1> observers;  // تغيير Observer إلى observer1

    private TaskManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    // إضافة observer1
    public void addObserver(Observer1 observer) {
        observers.add(observer);
    }

    // إخطار observers
    private void notifyObservers() {
        for (Observer1 observer : observers) {
            observer.update();  // استدعاء دالة update() في observer1
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        notifyObservers();
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        notifyObservers();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
