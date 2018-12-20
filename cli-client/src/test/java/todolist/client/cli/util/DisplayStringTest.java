package todolist.client.cli.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import todolist.common.Importance;

class DisplayStringTest {
	
	//TODO Test cases for date
	
	@Test
	public void highImportanceTest() {
		assertEquals("@|red High|@", DisplayString.of(Importance.HIGH));
	}
	
	@Test
	public void mediumImportanceTest() {
		assertEquals("@|yellow Medium|@", DisplayString.of(Importance.MEDIUM));
	}
	
	@Test
	public void lowImportanceTest() {
		assertEquals("@|green Low|@", DisplayString.of(Importance.LOW));
	}
	
	@Test
	public void noneImportanceTest() {
		Importance i = null;
		assertEquals("", DisplayString.of(i));
	}
	
	@Test
	public void trueBooleanTest() {
		assertEquals("@|green V|@", DisplayString.of(true));
    }
	
	@Test
	public void falseBooleanTest() {
		assertEquals("@|red X|@", DisplayString.of(false));
	}

}
