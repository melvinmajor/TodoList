package todolist.client.cli.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ExitActionTest {

	static ExitAction exitAction;

	@BeforeAll
	public static void setUp() {
		exitAction = new ExitAction();
		exitAction.getName();
		exitAction.usage();
	}

	@Test
	public void getNameTest() {
		assertEquals("exit", exitAction.getName());
	}

	@Test
	public void usageTest() {
		assertEquals("Exit the program", exitAction.usage());
	}

}