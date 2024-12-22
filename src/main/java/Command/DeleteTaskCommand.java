package Command;

import controller.TaskController;
import model.Task;

public class DeleteTaskCommand implements Command {

    private TaskController controller;
    private Task task;

    public DeleteTaskCommand(TaskController controller, Task task) {
        this.controller = controller;
        this.task = task;
    }

    @Override
    public void execute() {
        controller.removeTask(task);
    }
}
