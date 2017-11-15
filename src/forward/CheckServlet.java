package forward;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckServlet extends GenericServlet{
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String message = null;
        if(username == null){
            message = "please input username";
        }else{
            message = "Hello," + username;
        }

        req.setAttribute("msg", message);
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/output");

        PrintWriter printWriter = res.getWriter();
        printWriter.println("Output from CheckServlet before forwarding request");

        dispatcher.forward(req, res);

        printWriter.println("output from CheckServlet after forwarding request");
    }
}
