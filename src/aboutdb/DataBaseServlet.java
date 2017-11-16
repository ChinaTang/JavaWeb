package aboutdb;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class DataBaseServlet extends HttpServlet {

    private BookDB bookDB;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            bookDB = new BookDB();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        ArrayList<BookDetails> collection = null;
        PrintWriter printWriter = response.getWriter();
        try {
            collection = bookDB.getBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        printWriter.println("<html><head><title>MainServlet</title></head>");
        printWriter.println("<body>");

        if(collection != null){
            for(int i= 0; i < collection.size(); i++){
                BookDetails bookDetails = collection.get(i);
                printWriter.println("<br>" + bookDetails.toString() + "</br>");
            }
        }

        printWriter.println("</body></html>");
        printWriter.close();
    }
}
