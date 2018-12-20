package todolist.client.cli.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CompleteActionTest {

	static CompleteAction completeAction;

	@BeforeAll
	public static void setUp() {
		completeAction = new CompleteAction();
		completeAction.getName();
		completeAction.usage();
	}

	@Test
	public void getNameTest() {
		assertEquals("complete", completeAction.getName());
	}

	@Test
	public void usageTest() {
		assertEquals("Complete a task", completeAction.usage());
	}

}