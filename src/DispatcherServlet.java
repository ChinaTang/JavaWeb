

import javax.servlet.*;
import java.io.IOException;

/**
 *
 */
public class DispatcherServlet extends GenericServlet{

    private String userName;

    private String password;

    private ServletContext servletContext;

    private String tagjsp = "/hello.jsp";

    public static final String USE = "use";

    public static final String PASS = "pwd";

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        userName = servletRequest.getParameter("username");
        password = servletRequest.getParameter("password");

        if(userName == null || userName.equals("")){
        }

        servletRequest.setAttribute(USE, userName);
        servletRequest.setAttribute(PASS, password);

        servletContext = servletRequest.getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(tagjsp);
        requestDispatcher.forward(servletRequest, servletResponse);
    }
}
