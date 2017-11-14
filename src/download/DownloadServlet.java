package download;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DownloadServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OutputStream outputStream;
        InputStream inputStream;
        String fileName = request.getParameter("filename");
        if(fileName == null){
            outputStream = response.getOutputStream();
            outputStream.write("please input file name.".getBytes());
            outputStream.close();
            return;
        }

        inputStream = new FileInputStream("/home/tangdi/.bashrc");
        int length = inputStream.available();
        response.setContentType("application/force-download");
        response.setHeader("Content-Length", String.valueOf(length));
        response.setHeader("Content-Disposition", "attachment;filename=" + File.separator + fileName);

        outputStream = response.getOutputStream();
        int byteRead = 0;
        byte[] buffer = new byte[512];
        while((byteRead = inputStream.read(buffer))!= -1){
            outputStream.write(buffer, 0, byteRead);
        }
        inputStream.close();
        outputStream.close();
    }

}
