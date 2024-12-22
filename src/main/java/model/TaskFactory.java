//Factory pattern
package model;

public class TaskFactory {

    public static Task createTask(String name, String description) {
        return new Task(name, description);
    }
}
