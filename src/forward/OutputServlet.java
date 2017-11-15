package forward;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputServlet extends GenericServlet{
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        String message = (String)req.getAttribute("msg");
        PrintWriter printWriter = res.getWriter();
        printWriter.println(message);
        printWriter.close();
    }
}
