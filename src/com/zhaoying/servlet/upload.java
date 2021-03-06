package com.zhaoying.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/upload/form")
public class upload extends HttpServlet {

    public upload() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("upload");
        PrintWriter out = response.getWriter();
        DiskFileItemFactory factory = new DiskFileItemFactory();

        String path = request.getRealPath("/imgs");
        System.out.println(path);
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        factory.setRepository(new File(path));
        factory.setSizeThreshold(1024*1024);

        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
            for (FileItem item : list) {
                String name = item.getFieldName();
                if (item.isFormField()) {
                    String value = item.getString();
                    request.setAttribute(name, value);
                } else {
                    String value = item.getName();

                    int start = value.lastIndexOf("\\");

                    String filename = value.substring(start + 1);
                    request.setAttribute(name, filename);
                    item.write(new File(path, filename));
                    System.out.println("上传成功");
                    out.print(filename);
                }
            }
        }catch (Exception e){
            System.out.println("上传失败");
            out.print(e.getMessage());
            e.printStackTrace();
        }
        out.flush();
        out.close();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}