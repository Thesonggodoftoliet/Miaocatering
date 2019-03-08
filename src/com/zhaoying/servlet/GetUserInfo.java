package com.zhaoying.servlet;

import com.zhaoying.instance.User;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.service.impl.GetInfoSeImpl;
import com.zhaoying.utils.PraseUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/GetUserInfo")
public class GetUserInfo extends HttpServlet {

    public GetUserInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream js = request.getInputStream();
        InputStreamReader jsr = new InputStreamReader(js);
        BufferedReader br = new BufferedReader(jsr);

        String userjson = br.readLine();
        System.out.println("有东西吗"+userjson);


        JSONObject userObj = null;

        try {
            userObj = new JSONObject(userjson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        PrintWriter out =response.getWriter();
        User newUser = new User();

        try {
            newUser.setId(userObj.getInt("userID"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GetInfoSE getinfo = new GetInfoSeImpl();
        User sqluser = getinfo.getUserInfoById(newUser.getId());

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id",sqluser.getId());
            jsonObject.put("username",sqluser.getUsername());
            jsonObject.put("phone",sqluser.getPhone());
            jsonObject.put("address",sqluser.getAddress());
            jsonObject.put("sex",sqluser.getSex());
            jsonObject.put("ctime",sqluser.getCtime());
//            jsonObject.put("status",sqluser.getStatus());
//            jsonObject.put("passwd",sqluser.getPasswd());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("返回信息"+jsonObject);
        out.print(jsonObject);
        out.flush();
        out.close();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}