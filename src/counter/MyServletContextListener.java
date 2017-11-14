package counter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;

public class MyServletContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("helloapp application is initialized");

        ServletContext context = servletContextEvent.getServletContext();

        InputStream in = context.getResourceAsStream("/count/count.txt");

        InputStreamReader inputStream = new InputStreamReader(in);

        BufferedReader reader = new BufferedReader(inputStream);
        try {
            int count = Integer.parseInt(reader.readLine());
            reader.close();
            Counter counter = new Counter(count);
            context.setAttribute("counter", count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("helloapp application is Destroy");

        ServletContext context = servletContextEvent.getServletContext();
        Counter counter = (Counter)context.getAttribute("counter");
        if(counter != null){
            String filePath = context.getRealPath(File.separator + "count");
            filePath = filePath + File.separator + "count.txt";
            try {
                PrintWriter pw = new PrintWriter(filePath);
                pw.println(counter.getCount());
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
