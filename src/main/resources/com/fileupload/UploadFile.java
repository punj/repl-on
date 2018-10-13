/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileupload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Punj
 */
public class UploadFile extends HttpServlet
{

    private static final long serialVersionUID = 1L;
//        private final String UPLOAD_DIRECTORY = "/Unmatched/FileUploads";
    private   String LOCAL_DIRECTORY = "";
    private   String SERVER_DIRECTORY = "";

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    { 
        ServletContext context = getServletContext();
         //  String fullPath;
         LOCAL_DIRECTORY  = context.getRealPath("/uploads/getPath.jsp");
           LOCAL_DIRECTORY = LOCAL_DIRECTORY.replaceAll("getPath.jsp", "");
        System.out.println("sgn fullpath " +LOCAL_DIRECTORY);
         SERVER_DIRECTORY = "http://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath() +"/uploads";
         System.out.println("sgn server directory ** "+SERVER_DIRECTORY);
        LOCAL_DIRECTORY =LOCAL_DIRECTORY;
        PrintWriter out = response.getWriter();
        System.out.println("sgn ***");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        // process only if its multipart content
        if (isMultipart)
          {
            // Create a factory for disk-based file items
            FileItemFactory factory = new DiskFileItemFactory();

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            try
              {
                // Parse the request
                List<FileItem> multiparts = upload.parseRequest(request);

                for (FileItem item : multiparts)
                  {
                    if (!item.isFormField())
                      {
                        String originalFilename = new File(item.getName()).getName();

                        String extension = FilenameUtils.getExtension(new File(item.getName()).getName());
                        String newFileName = String.valueOf(System.nanoTime()) + "." + extension;
                        System.err.println("sgn FILE PATH " + (LOCAL_DIRECTORY +   newFileName));
                        item.write(new File(LOCAL_DIRECTORY +  newFileName));
                        out.println((SERVER_DIRECTORY +"/" +newFileName)); 
                        out.flush();
                        out.close();
                      }
                  }
              } catch (Exception e)
              {
                System.out.println("File upload failed");
              }
          }
    }
}
