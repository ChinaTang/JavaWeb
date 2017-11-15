package workspacedir;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class DirTesterServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext context = getServletContext();

        response.setContentType("text/html; charset=GB2312");
        PrintWriter printWriter = response.getWriter();
        Enumeration enumeration = context.getAttributeNames();
        while(enumeration.hasMoreElements()){
            String attributeName = (String)enumeration.nextElement();
            printWriter.println("<br>" + attributeName + ": " + context.getAttribute(attributeName));
        }
        printWriter.close();

        File workDir = (File)context.getAttribute("javax.servlet.context,tempdir");

        FileOutputStream fileOutputStream = new FileOutputStream(workDir + "tmp.txt");
        fileOutputStream.write("Hello by Tangdi".getBytes());
        fileOutputStream.close();
    }
}
