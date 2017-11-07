import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class RequestInfoServlet extends HttpServlet{

    private String userInit;

    private String passInit;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html");

        //resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        userInit = getInitParameter("user");
        passInit = getInitParameter("pass");

        if(userInit == null || userInit.equals("")){
            resp.sendError(HttpServletResponse.SC_ACCEPTED);
        }

        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>RequestInfoServlet</title></head>");
        writer.println("<body>");
        writer.println("<br> LocalAddress: " + req.getLocalAddr() + "</br>");
        writer.println("<br> LocalName: " + req.getLocalName() + "</br>");
        writer.println("<br> LocalPort: " + req.getLocalPort() + "</br>");
        writer.println("<br> Protocol: " + req.getProtocol() + "</br>");
        writer.println("<br> remoteAddress: " + req.getRemoteAddr() + "</br>");
        Enumeration<String> enumeration = req.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String headname = enumeration.nextElement();
            writer.println("<br>" + headname + ": " + req.getHeader(headname) + "</br>");
        }
        writer.println("</body></html>");

        writer.close();

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    }

}
