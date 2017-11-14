package upload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class UploadServlet extends HttpServlet {

    private String filePath;
    private String tempFilePath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        filePath = config.getInitParameter("filePath");
        tempFilePath = config.getInitParameter("tempFilePath");
        filePath = getServletContext().getRealPath(filePath);
        tempFilePath = getServletContext().getRealPath(tempFilePath);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        PrintWriter outNet = response.getWriter();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4*1024);
        factory.setRepository(new File(tempFilePath));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(100*1024*1024);
        try {
            List<FileItem> items = upload.parseRequest(request);
            Iterator iterator = items.iterator();
            while(iterator.hasNext()){
                FileItem item = (FileItem)iterator.next();
                if(item.isFormField()){
                    processFormField(item, outNet);
                }else{
                    processUploadedFile(item, outNet);
                }
            }
            outNet.close();
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processFormField(FileItem item, PrintWriter outNet){
        String name = item.getFieldName();
        String value = item.getString();
        outNet.println(name + ":" + value + "\r\n");
    }

    private void processUploadedFile(FileItem item, PrintWriter outNet) throws Exception {
        String fileName = item.getName();
        int index = fileName.lastIndexOf(File.separator);
        fileName = fileName.substring(index + 1, fileName.length());
        long fileSize = item.getSize();
        if(fileName.equals("") && fileSize == 0)return;

        File uploadedFile = new File(filePath + File.separator);
        item.write(uploadedFile);
        outNet.println(fileName + "is saved.");
        outNet.println("The size of " + fileName + "is " + fileSize + "\r\n");
    }

}
