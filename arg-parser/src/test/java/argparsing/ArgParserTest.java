package argparsing;

import org.junit.jupiter.api.Test;

import static argparsing.OptionFactory.newFlag;
import static argparsing.OptionFactory.newOption;
import static org.junit.jupiter.api.Assertions.*;

class ArgParserTest {

    @Test
    void shortFlagTest() {
        var opt = newFlag("s");
        new ArgParser(opt)
                .addSource(new String[]{"-s"})
                .parse();
        assertTrue(opt.isPresent());
    }


    @Test
    void longFlagTest() {
        var opt = newFlag("save");
        new ArgParser(opt)
                .addSource(new String[]{"--save"})
                .parse();
        assertTrue(opt.isPresent());
    }

    @Test
    void OptionTest() {
        var opt = newOption("o", "output");
        new ArgParser(opt)
                .addSource(new String[]{"--output", "abcd"})
                .parse();
        assertEquals("abcd", opt.getOptional().orElse(null));
    }

    @Test
    void OptionEqualsTest() {
        var opt = newOption("o", "output");
        new ArgParser(opt)
                .addSource(new String[]{"--output=abcd"})
                .parse();
        assertEquals("abcd", opt.getOptional().orElse(null));
    }

    @Test
    void IntOptionTest() {
        var opt = newOption("p", "port");
        new ArgParser(opt)
                .addSource(new String[]{"--port=8000"})
                .parse();
        assertEquals(Integer.valueOf(8000), opt.getOptionalInt().orElse(null));
    }

    @Test
    void mixedTest() {
        var saveFlg = newFlag("s", "save");
        var notPresentFlg = newFlag("idk");
        var portOpt = newOption("p", "port");
        var logLvlOpt = newOption("log-level");

        new ArgParser(saveFlg, notPresentFlg, portOpt, logLvlOpt)
                .addSource(new String[]{"--port=8000", "-s", "--log-level", "debug"})
                .parse();

        assertTrue(saveFlg.isPresent());
        assertFalse(notPresentFlg.isPresent());
        assertEquals(Integer.valueOf(8000), portOpt.getOptionalInt().orElse(null));
        assertEquals("debug", logLvlOpt.getOptional().orElse(null));
    }

    @Test
    void multiSourceTest() {
        var aFlag = newFlag("a");
        var bFlag = newFlag("b");
        var portOpt = newOption("p", "port");

        new ArgParser(aFlag, bFlag, portOpt)
                .addSource(new String[]{"-a", "--port=8000"})
                .addSource(new String[]{"-b"})
                .parse();

        assertTrue(aFlag.isPresent());
        assertTrue(bFlag.isPresent());
        assertEquals(Integer.valueOf(8000), portOpt.getOptionalInt().orElse(null));
    }

    @Test
    void multiSourceOverrideTest() {
        var portOpt = newOption("p", "port");

        new ArgParser(portOpt)
                .addSource(new String[]{"--port=9000"})
                .addSource(new String[]{"--port=9001"})
                .parse();

        assertTrue(portOpt.getOptionalInt().orElse(-1) > 9000);
    }

}