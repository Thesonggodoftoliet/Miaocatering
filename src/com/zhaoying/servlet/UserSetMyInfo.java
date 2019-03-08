package com.zhaoying.servlet;

import com.zhaoying.instance.User;
import com.zhaoying.service.UpdateUserInfoSE;
import com.zhaoying.service.impl.UpdateUserInfoSEImpl;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/UserSetMyInfo")
public class UserSetMyInfo extends HttpServlet {

    public UserSetMyInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/api/UserSetMyInfo");
        PrintWriter out = response.getWriter();
        JSONObject object = ReciveUtils.getObject(request);
        User tuser = new User();
        try{
            tuser.setId(object.getInt("UserId"));
            tuser.setPasswd(object.getString("pwd"));
            tuser.setUsername(object.getString("username"));
            tuser.setAddress(object.getString("address"));
            tuser.setPhone(object.getString("phone"));
            tuser.setSex(object.getInt("sex"));
        }catch (JSONException e){
            e.printStackTrace();
        }
        UpdateUserInfoSE userInfoSE = new UpdateUserInfoSEImpl();
        String tag = userInfoSE.setUser(tuser);
        JSONObject msg = new JSONObject();
        try{
            msg.put("stat",tag);
        }catch (JSONException e){
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