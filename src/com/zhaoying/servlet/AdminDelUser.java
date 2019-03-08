package com.zhaoying.servlet;

import com.zhaoying.service.UpdateUserInfoSE;
import com.zhaoying.service.impl.UpdateUserInfoSEImpl;
import com.zhaoying.utils.PraseUtils;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/AdminDelUser")
public class AdminDelUser extends HttpServlet {

    public AdminDelUser() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = ReciveUtils.getObject(request);
        UpdateUserInfoSE use = new UpdateUserInfoSEImpl();
        String tag = null;
        try {
            tag = use.deleteUser(jsonObject.getInt("userID"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();

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