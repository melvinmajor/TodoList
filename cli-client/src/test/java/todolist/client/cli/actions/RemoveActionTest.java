package todolist.client.cli.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RemoveActionTest {

	static RemoveAction removeAction;

	@BeforeAll
	public static void setUp() {
		removeAction = new RemoveAction();
		removeAction.getName();
		removeAction.usage();
	}

	@Test
	public void getNameTest() {
		assertEquals("remove", removeAction.getName());
	}

	@Test
	public void usageTest() {
		assertEquals("Remove a task", removeAction.usage());
	}

}