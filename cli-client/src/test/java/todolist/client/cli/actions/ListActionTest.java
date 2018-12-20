package todolist.client.cli.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ListActionTest {

	static ListAction listAction;

	@BeforeAll
	public static void setUp() {
		listAction = new ListAction();
		listAction.getName();
		listAction.usage();
	}

	@Test
	public void getNameTest() {
		assertEquals("list", listAction.getName());
	}

	//TODO test cases for execute
	
	@Test
	public void usageTest() {
		assertEquals("List all tasks", listAction.usage());
	}

}