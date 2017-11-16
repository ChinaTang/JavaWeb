package include;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        PrintWriter printWriter = response.getWriter();
        printWriter.println("<html><head><title>MainServlet</title></head>");
        printWriter.println("<body>");

        ServletContext context = getServletContext();
        RequestDispatcher headDispatcher = context.getRequestDispatcher("/header.html");
        RequestDispatcher greetDispatcher = context.getRequestDispatcher("/greet");
        RequestDispatcher footerDispatcher = context.getRequestDispatcher("/footer.html");

        headDispatcher.include(request, response);
        greetDispatcher.include(request, response);
        footerDispatcher.include(request, response);

        printWriter.println("</body></html>");
        printWriter.close();
    }
}
