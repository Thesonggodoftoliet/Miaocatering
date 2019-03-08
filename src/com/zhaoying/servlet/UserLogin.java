package com.zhaoying.servlet;

import com.zhaoying.instance.User;
import com.zhaoying.service.LoginSE;
import com.zhaoying.service.impl.LoginSEImpl;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
//用户登陆接口
@WebServlet("/api/UserLogin")
public class UserLogin extends HttpServlet {

    public UserLogin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject userObj = ReciveUtils.getObject(request);

        PrintWriter out =response.getWriter();
        User newUser = new User();

        try {
            newUser.setPhone(userObj.getString("phone"));
            newUser.setPasswd(userObj.getString("passwd"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LoginSE lse = new LoginSEImpl();
        String tag = lse.userCheck(newUser);


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