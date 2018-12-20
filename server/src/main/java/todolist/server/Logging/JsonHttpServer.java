package todolist.server.Logging;

import com.sun.net.httpserver.HttpServer;
import todolist.server.Server;

import java.io.IOException;
import java.net.InetSocketAddress;

public class JsonHttpServer {
    private final int port;
    private HttpServer httpServer;

    public JsonHttpServer(int port) {
        this.port = port;
    }

    public void start() {
        Server.logger.info("Starting http server @ port " + port);
        try {
            httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            httpServer.createContext("/tasks", exchange -> {
                byte[] response = "test".getBytes();
                exchange.sendResponseHeaders(200, response.length);
                var outputStream = exchange.getResponseBody();
                outputStream.write(response);
                outputStream.close();
            });
            httpServer.setExecutor(null);
            httpServer.start();
            Server.logger.info("Started http server @ port " + port);
        } catch (IOException e) {
            Server.logger.error(e.getMessage());
        }
    }

    public void stop() {
        Server.logger.info("Stopping http server @ port");
        httpServer.stop(2);
        Server.logger.info("Stopped http server @ port");
    }

}
