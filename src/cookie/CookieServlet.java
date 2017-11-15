package cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CookieServlet extends HttpServlet{
    int count = 0;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        PrintWriter printWriter = response.getWriter();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(int i = 0; i < cookies.length; i++){
                printWriter.println("Cookie name: " + cookies[i].getName());
                printWriter.println("Cookie value: " + cookies[i].getValue());
            }
        }else{
            printWriter.println("No cookie");
        }
        response.addCookie(new Cookie("cookieName" + count, "cookieValue" + count));
        count++;
    }
}
