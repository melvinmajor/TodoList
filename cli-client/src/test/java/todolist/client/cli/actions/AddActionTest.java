package todolist.client.cli.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AddActionTest {

	static AddAction addAction;

	@BeforeAll
	public static void setUp() {
		addAction = new AddAction();
		addAction.getName();
		addAction.usage();
	}

	@Test
	public void getNameTest() {
		assertEquals("add", addAction.getName());
	}
	
	//TODO test cases for execute

	@Test
	public void usageTest() {
		assertEquals("Add a task", addAction.usage());
	}

}