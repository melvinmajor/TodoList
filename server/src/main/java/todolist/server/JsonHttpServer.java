package todolist.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import todolist.server.logging.Logger;
import todolist.server.serialization.SerializableTask;
import todolist.server.serialization.TaskSerialization;

import java.io.IOException;
import java.net.InetSocketAddress;

public class JsonHttpServer {
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String JSON_CONTENT_TYPE = "application/json; charset=UTF-8";

    private static final String METHOD_GET = "GET";
    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String ALLOWED_METHODS = METHOD_GET + "," + METHOD_OPTIONS;

    private static final String HEADER_ALLOW = "Allow";

    private final Logger logger = new Logger("HTTP server");

    private final int port;
    private HttpServer httpServer;


    public JsonHttpServer(int port) {
        this.port = port;
    }

    public void start() {
        logger.info("Starting http server @ port " + port);
        try {
            httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            httpServer.createContext("/tasks", this::sendResponse);
            httpServer.setExecutor(null);
            httpServer.start();
            logger.info("Started http server @ port " + port);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void sendResponse(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod().toUpperCase();
        logger.info("received " + requestMethod + " request");
        switch (requestMethod) {
            case METHOD_GET:
                exchange.getResponseHeaders().set(HEADER_CONTENT_TYPE, JSON_CONTENT_TYPE);
                var response = getJsonTasks().getBytes();
                exchange.sendResponseHeaders(200, response.length);
                var outputStream = exchange.getResponseBody();
                outputStream.write(response);
                outputStream.close();
                break;
            case METHOD_OPTIONS:
                exchange.getResponseHeaders().set(HEADER_ALLOW, ALLOWED_METHODS);
                exchange.sendResponseHeaders(200, -1);
                break;
            default:
                exchange.getResponseHeaders().set(HEADER_ALLOW, ALLOWED_METHODS);
                exchange.sendResponseHeaders(405, -1);
        }

    }

    public void stop() {
        logger.info("Stopping http server @ port");
        httpServer.stop(2);
        logger.info("Stopped http server @ port");
    }

    private String getJsonTasks() {
        var tasks = Server.instance.taskManager.getTasks();
        var serializableTasks = tasks.stream()
                .map(SerializableTask::new)
                .toArray(SerializableTask[]::new);

        return TaskSerialization.toJson(serializableTasks);
    }

}
