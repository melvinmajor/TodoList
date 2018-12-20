package todolist.server.Logging;

import java.time.LocalDateTime;

public class Logger {
    private final String name;

    public Logger(Class clazz) {
        this.name = clazz.getSimpleName();
    }

    public Logger(String name) {
        this.name = name;
    }

    public void info(String msg) {
        log(msg, LogLevel.INFO);
    }

    public void warn(String msg) {
        log(msg, LogLevel.WARN);
    }

    public void error(String msg) {
        log(msg, LogLevel.ERROR);
    }

    private void log(String msg, LogLevel logLevel) {
        var out = LocalDateTime.now() + " "
                + "[" + name + "] "
                + logLevel.name() + " "
                + msg;

        // TODO logFile ?
        System.out.println(out);
    }

    private enum LogLevel {
        INFO, WARN, ERROR
    }

}
