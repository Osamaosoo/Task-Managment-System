package Command;

import model.Task;

public class MarkCompletedTaskCommand implements Command {

    private Task task;

    public MarkCompletedTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute() {
        task.markComplete();
    }
}
