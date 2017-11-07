package counter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CounterServlet extends HttpServlet {

    private static final String COUNTER = "counter";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();

        Counter counter = (Counter)context.getAttribute(COUNTER);

        if(counter == null){
            counter = new Counter(1);
            context.setAttribute(COUNTER, counter);
        }

        resp.setContentType("text/html; charset=GB2312");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>RequestInfoServlet</title></head>");
        writer.println("<body>");
        writer.println("<h1>访问次数" + counter.getCount() + "</h1>");
        writer.println("</body></html>");
        counter.add(1);
        writer.close();
    }
}
