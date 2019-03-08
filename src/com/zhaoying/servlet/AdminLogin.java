package com.zhaoying.servlet;

import com.zhaoying.instance.Admin;
import com.zhaoying.service.LoginSE;
import com.zhaoying.service.impl.LoginSEImpl;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/AdminLogin")
public class AdminLogin extends HttpServlet {

    public AdminLogin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream js = request.getInputStream();
        InputStreamReader jsr = new InputStreamReader(js);
        BufferedReader br = new BufferedReader(jsr);

        String adminjson = br.readLine();
        System.out.println("有东西吗"+adminjson);


        JSONObject adminObj = null;

        try {
            adminObj = new JSONObject(adminjson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        PrintWriter out =response.getWriter();
        Admin admin = new Admin();
        try {
            admin.setPhone(adminObj.getString("phone"));
            admin.setPasswd(adminObj.getString("passwd"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LoginSE lse = new LoginSEImpl();
        String tag = lse.adminCheck(admin);

        System.out.println("tag = "+tag);

        JSONObject jo = new JSONObject();
        if (!(tag.equals("-1")||tag.equals("0"))){
            String userid = tag;
            tag = "1";
            System.out.println(tag);
            try {
                jo.put("UserId",userid);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                jo.put("UserId","0");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            jo.put("stat",tag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        out.print(jo);
        out.flush();
        out.close();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}