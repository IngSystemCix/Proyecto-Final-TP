package pe.edu.utp.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyServer {
    public static void main(String[] args) throws Exception {
        int port = 8080; // Puerto en el que se ejecutará el servidor

        Server server = new Server(port);

        // Configuración del contexto de la aplicación web
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        webAppContext.setWar("src/main/java/pe/edu/utp/webapp"); // Ruta al directorio webapp de tu proyecto

        // Configuración del handler del servidor
        HandlerCollection handlers = new HandlerCollection();
        handlers.addHandler(webAppContext);

        server.setHandler(handlers);

        server.start();
        server.join();
    }
}







