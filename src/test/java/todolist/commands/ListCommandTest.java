package todolist.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.Test;

import todolist.Importance;
import todolist.Main;
import todolist.Task;

class ListCommandTest {

	@Test
	void test() {
		Task task = new Task("Test Description", Importance.HIGH, LocalDate.now(), LocalDate.now(),
				Set.of("EPHEC"), Set.of("John Doe"), false);
		Main.taskManager.addTask(task);

		new ListCommand().execute();

		assertTrue(true);
	}

}
