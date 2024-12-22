package Command;

import controller.TaskController;

public class AddTaskCommand implements Command {

    private TaskController controller;
    private String name;
    private String description;

    public AddTaskCommand(TaskController controller, String name, String description) {
        this.controller = controller;
        this.name = name;
        this.description = description;
    }

    @Override
    public void execute() {
        controller.addTask(name, description);
    }
}
