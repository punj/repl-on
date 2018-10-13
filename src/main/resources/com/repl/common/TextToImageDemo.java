/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.common;

 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;

/**
 * @author Matthew Jones, 2009
 */
public class TextToImageDemo extends CommonAction{

    public String texttoimage(String text)
    {
        System.out.println("textToImage Called");
        try {

            // collect correct inputs or DIE.
            //String email = "Conversion ";
            //String text="Tax Invoice/Retail Invoice";
            Color fg = new Color(ColorSpace.CS_GRAY);
            Color bg = new Color(ColorSpace.TYPE_GRAY);
            
             System.out.println(text);
            //String filename = "Images.png";
            //String path = "c:\\images\\Images.png";
           // System.out.println(path);

            // call render image method.
            RenderedImage rendImage=null;
             BufferedImage buffRenderImage=null;
            try
            {
             //rendImage = writeImage(text, fg, bg);
           // RenderedImage rendImage = writeImage(email, fg, bg);
                int width = (int) ((text.length() * 7.0) + 8);

        // standard height requirement of 16 px.
                int height = 40;
                buffRenderImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
       // BufferedImage rounded = makeRoundedCorner(buffRenderImage, 20);
                Graphics2D flatGraphic = buffRenderImage.createGraphics();
                int w = buffRenderImage.getWidth();
                int h = buffRenderImage.getHeight();

        // Draw background
       // flatGraphic.setColor();
                flatGraphic.fill(new RoundRectangle2D.Float(0, 0, w, h, 5, 5));
       // flatGraphic.fillRect(0, 0, width, height);
       

        //Draw text
                flatGraphic.setColor(Color.BLACK);
                Font font = new Font("Times Roman", Font.BOLD, 14);
                flatGraphic.setFont(font);
                flatGraphic.drawString(text, 5, 25);
        //flatGraphic.drawImage(buffRenderImage, 0 , 0 , null);

        // don't use drawn graphic anymore.
                flatGraphic.dispose();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
              ServletContext servletcontext = ServletActionContext.getServletContext();
                String path1 = servletcontext.getRealPath("/");
                String imagePath = path1 + "userimages" + "/Images.jpg";
                File file = new File(imagePath);
            

                ImageIO.write(buffRenderImage, "png", file);

        } catch (Exception e) {
            // Sloppy Error handling below
            System.out.println("Usage: textToImage.jar email fg-colour-hex bg-colour-hex filename");
            System.out.println("Example: textToImage.jar eg@eg.com FFFFFF 0000FF C:\\dir\\image.png");
           // System.out.print(e.getMessage());
            e.printStackTrace();
        }
        return "login";
    }

    public RenderedImage writeImage(String text, Color fgc, Color bgc) {

        // calculate image size requirements.
        int width = (int) ((text.length() * 6.2) + 5);

        // standard height requirement of 16 px.
        int height = 56;
        BufferedImage buffRenderImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
       // BufferedImage rounded = makeRoundedCorner(buffRenderImage, 20);
        Graphics2D flatGraphic = buffRenderImage.createGraphics();
        int w = buffRenderImage.getWidth();
        int h = buffRenderImage.getHeight();

        // Draw background
       // flatGraphic.setColor();
        flatGraphic.fill(new RoundRectangle2D.Float(0, 0, w, h, 2, 8));
       // flatGraphic.fillRect(0, 0, width, height);
       

        //Draw text
        flatGraphic.setColor(Color.BLACK);
        Font font = new Font("Times Roman", Font.BOLD, 14);
        flatGraphic.setFont(font);
        flatGraphic.drawString(text, 0, 65);
        //flatGraphic.drawImage(buffRenderImage, 0 , 0 , null);

        // don't use drawn graphic anymore.
        flatGraphic.dispose();
        
        return buffRenderImage;
    }
    /*public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
    int w = image.getWidth();
    int h = image.getHeight();
    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2 = output.createGraphics();

    // This is what we want, but it only does hard-clipping, i.e. aliasing
    // g2.setClip(new RoundRectangle2D ...)

    // so instead fake soft-clipping by first drawing the desired clip shape
    // in fully opaque white with antialiasing enabled...
    g2.setComposite(AlphaComposite.Src);
    //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(Color.WHITE);
    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

    // ... then compositing the image on top,
    // using the white shape from above as alpha source
    g2.setComposite(AlphaComposite.SrcAtop);
    g2.drawImage(image, 0, 0, null);

    g2.dispose();

    return output;
}*/

}