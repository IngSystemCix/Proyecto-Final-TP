import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/servlet-jetty")
public class ServletJetty extends HttpServlet {
    protected void doGet(HttpServletRequest request, @org.jetbrains.annotations.NotNull HttpServletResponse response) throws IOException {
        response.getWriter().println("¡Hola desde ServletJetty!");
    }
}