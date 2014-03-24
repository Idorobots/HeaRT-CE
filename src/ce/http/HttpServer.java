package ce.http;

import java.io.IOException;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
//import com.sun.net.httpserver.HttpServer;

public class HttpServer {
    public static final String API_VERSION = "v1.0";
    private String host = null;
    private Integer port = -1;
    private com.sun.net.httpserver.HttpServer server = null;

    public HttpServer(String host, int port) {
        this.host = host;
        this.port = port;

        try {
            String baseUri = "http://" + this.host + ":" + this.port.toString() + "/" + API_VERSION + "/";
            this.server = HttpServerFactory.create(baseUri);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        this.server.start();
    }

    public void stop() {
        this.server.stop(0);
    }
}