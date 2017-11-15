package cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CookieUserServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie cookie = null;
        response.setContentType("text/plain");
        PrintWriter printWriter = response.getWriter();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(int i = 0; i < cookies.length; i++){
                printWriter.println("Cookie name: " + cookies[i].getName());
                printWriter.println("Cookie value: " + cookies[i].getValue());
                if(cookies[i].getName().equals("username")){
                    cookie = cookies[i];
                }
            }
        }else{
            printWriter.println("No cookie");
        }

        if(cookie == null){
            cookie = new Cookie("username", "Tom");
            cookie.setMaxAge(60*60);
            response.addCookie(cookie);
        }else if(cookie.getValue().equals("Tom")){
            cookie.setValue("Jack");
            response.addCookie(cookie);
        }else if(cookie.getValue().equals("Jack")){
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

    }

}
