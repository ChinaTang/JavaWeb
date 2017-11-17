package session;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionDemo extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        session.setAttribute("data", "tangdi");
        String sessionId = session.getId();

        if(session.isNew()){
            response.getWriter().println("session创建成功 ： " + sessionId);
        }else{
            response.getWriter().println("已有session: " + sessionId);
        }
    }
}


