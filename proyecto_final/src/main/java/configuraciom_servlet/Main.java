package configuraciom_servlet;

import jakarta.servlet.Servlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080); // Puerto en el que se ejecutará Jetty

        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(ServletJetty.class, "/configuraciom_servlet");

        server.setHandler(handler);

        server.start();
        server.join();
    }
}

