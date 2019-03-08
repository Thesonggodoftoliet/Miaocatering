package com.zhaoying.servlet;

import com.zhaoying.service.UpdateResInfoSE;
import com.zhaoying.service.impl.UpdateResInfoSEImpl;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/AdminDelRestau")
public class AdminDelRestau extends HttpServlet {

    public AdminDelRestau() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = ReciveUtils.getObject(request);
        UpdateResInfoSE use = new UpdateResInfoSEImpl();
        String tag = null;
        try {
            tag = use.deleteResById(jsonObject.getInt("restaurantID"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject msg = new JSONObject();
        try {
            msg.put("stat",tag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("返回信息"+msg);
        out.print(msg);
        out.flush();
        out.close();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}