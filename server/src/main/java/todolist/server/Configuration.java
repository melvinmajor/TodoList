package todolist.server;

import todolist.common.Task;
import todolist.server.logging.Logger;
import todolist.server.serialization.SerializableTask;
import todolist.server.serialization.TaskSerialization;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Configuration {
    private final Logger logger = new Logger(Configuration.class);

    private final TaskManager taskManager;
    private final Path saveFilePath;
    private boolean loaded;

    public Configuration(TaskManager taskManager, String path) {
        this.taskManager = taskManager;
        this.saveFilePath = Path.of(path);
    }

    public void restore() {
        if (loaded) {
            logger.warn("Configuration can only be loaded at start, ignoring");
            return;
        }

        if (!saveFilePath.toFile().exists()) {
            logger.warn(saveFilePath + " doesn't exist yet");
            return;
        }

        List<String> strings;
        try {
            strings = Files.readAllLines(saveFilePath);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return;
        }
        var json = String.join("\n", strings);

        restore(TaskSerialization.fromJson(json));

        logger.info("Restored tasks from" + saveFilePath);

        loaded = true;
    }

    private void restore(SerializableTask[] serializableTasks) {
        int id = 0;

        var tasks = new ArrayList<Task>();
        for (var serializableTask : serializableTasks) {
            var task = serializableTask.toTaskBuilder().setId(id++);
            if (task.validate()) tasks.add(task.build());
        }

        tasks.forEach(taskManager::addOrEditTask);
    }

    public void save() {
        SerializableTask[] serializableTasks = taskManager.getTasks().stream()
                .map(SerializableTask::new)
                .toArray(SerializableTask[]::new);

        var json = TaskSerialization.toJson(serializableTasks);

        try {
            Files.writeString(saveFilePath, json);
            logger.info("Saved tasks to" + saveFilePath);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }


}
