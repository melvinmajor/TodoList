package todolist.client.cli.parsing;

import todolist.common.Task;

public class TaskByIdParser implements Parser<Task> {
    private final Task[] tasks;

    public TaskByIdParser(Task[] tasks) {
        this.tasks = tasks;
    }

    @Override
    public Task parse(String s) throws ParseException {
        int id;
        try {
            id = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new ParseException();
        }
        if (id < 0 || id > tasks.length - 1) throw new ParseException();

        return tasks[id];
    }

    @Override
    public String onError() {
        return "invalid ID";
    }

}
