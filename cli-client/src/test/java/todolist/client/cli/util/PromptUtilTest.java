package todolist.client.cli.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PromptUtilTest {

    @Test
    void NPETest() {
        assertThrows(NullPointerException.class, () -> new PromptUtil<>(null).getOptional());
    }

}