package com.zhaoying.servlet;

import com.zhaoying.instance.User;
import com.zhaoying.service.RegisterSE;
import com.zhaoying.service.impl.RegisterSEImpl;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/UserRegister")
//用户注册接口
public class UserRegister extends HttpServlet {

    public UserRegister() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/api/UserRegister");
        JSONObject userObj = ReciveUtils.getObject(request);
        PrintWriter out =response.getWriter();
        User newUser = new User();

        try {
            newUser.setAddress(userObj.getString("address"));
            newUser.setUsername(userObj.getString("username"));
//            newUser.setCtime((int)System.currentTimeMillis());
            newUser.setPasswd(userObj.getString("passwd"));
            newUser.setPhone(userObj.getString("phone"));
            newUser.setSex(userObj.getInt("sex"));
            newUser.setCtime(System.currentTimeMillis());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RegisterSE rse = new RegisterSEImpl();
        String tag = rse.addUser(newUser);

        JSONObject msg = new JSONObject();
        try {
            msg.put("stat",tag);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        out.print(msg);
        out.flush();
        out.close();
        System.out.println("测试返回"+msg);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}