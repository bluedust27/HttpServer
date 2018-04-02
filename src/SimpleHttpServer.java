import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/info", new MyHandler());
        server.createContext("/",new RootHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Started");
    }

    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            byte [] response = "Welcome Real's HowTo test page".getBytes();
            t.sendResponseHeaders(201, response.length);
            OutputStream os = t.getResponseBody();
            os.write(response);
            os.close();
        }
    }

   static  public class RootHandler implements HttpHandler {

        @Override

        public void handle(HttpExchange he) throws IOException {
            String response = "<h1>Server start success  if you see this message</h1>" + "<h1>Port: 8000</h1>";
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
