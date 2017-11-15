package image;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageServlet extends HttpServlet{
    private Font font = new Font("Courier", Font.BOLD, 12);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String count = request.getParameter("count");
        if(count == null) count = "1";

        int len = count.length();

        response.setContentType("image/jpeg");

        ServletOutputStream outputStream = response.getOutputStream();

        BufferedImage image = new BufferedImage(11 * len, 16, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, 11*len, 16);
        g.setColor(Color.white);
        g.setFont(font);

        char c;
        for(int i = 0; i < len; i++){
            c = count.charAt(i);
            g.drawString(c + "", i*11 + 1, 12);
            g.drawLine((i + 1)*11-1, 0, (i+1)*11-1, 16);
        }

        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
        encoder.encode(image);
        outputStream.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
