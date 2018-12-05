package todolist.client.cli.parsing;

import todolist.client.cli.util.TaskUtil;
import todolist.common.Task;

class TaskByIdParser implements Parser<Task> {
    @Override
    public Task parse(String s) throws ParseException {
        int id;
        try {
            id = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new ParseException();
        }

        Task task = TaskUtil.getTaskById(id);
        if (task == null) throw new ParseException();

        return null;
    }

    @Override
    public String onError() {
        return "invalid ID";
    }

    @Override
    public Type type() {
        return Type.ID;
    }
}
