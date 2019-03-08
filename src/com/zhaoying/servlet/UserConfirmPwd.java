package com.zhaoying.servlet;

import com.zhaoying.instance.User;
import com.zhaoying.service.LoginSE;
import com.zhaoying.service.impl.LoginSEImpl;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/UserConfirmPwd")
public class UserConfirmPwd extends HttpServlet {

    public UserConfirmPwd() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/api/UserConfirmPwd");
        PrintWriter out = response.getWriter();
        JSONObject object = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        User tuser = new User();
        try {
            tuser.setId(object.getInt("UserId"));
            tuser.setPasswd(object.getString("pwd"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LoginSE loginSE = new LoginSEImpl();
        try {
            if (loginSE.userCheck(tuser).equals("0"))
                msg.put("stat", "0");
            else
                msg.put("stat","1");
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