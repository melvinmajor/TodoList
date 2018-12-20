package todolist.client.cli.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class HelpActionTest {

	static HelpAction helpAction;

	@BeforeAll
	public static void setUp() {
		helpAction = new HelpAction();
		helpAction.getName();
		helpAction.usage();
	}

	@Test
	public void getNameTest() {
		assertEquals("help", helpAction.getName());
	}

	//TODO test cases for execute
	
	@Test
	public void usageTest() {
		assertEquals("Print this help message", helpAction.usage());
	}

}