package com.zhaoying.servlet;

import com.zhaoying.instance.Admin;
import com.zhaoying.instance.User;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.service.impl.GetInfoSeImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/AdminGetAllUser")
public class AdminGetAllUser extends HttpServlet {

    public AdminGetAllUser() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("管理员要全部信息");
        InputStream js = request.getInputStream();
        InputStreamReader jsr = new InputStreamReader(js);
        BufferedReader br = new BufferedReader(jsr);

        String adminjson = br.readLine();

        PrintWriter out = response.getWriter();

        JSONObject jsonObject =null;
        try {
            jsonObject = new JSONObject(adminjson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Admin admin = new Admin();
        try {
            admin.setId(jsonObject.getInt("UserId"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<User> userlist = null;

        GetInfoSE gse = new GetInfoSeImpl();
        String stat;
        JSONArray user = new JSONArray();
        if (gse.getAdminInfoById(admin.getId()) == null) stat="0";//没有此管理员
        else {
            userlist = gse.getAllUser();
            for (int i = 0;i<userlist.size();i++){
                JSONObject jo = new JSONObject();
                try {
                    jo.put("id",userlist.get(i).getId());
                    jo.put("ctime",userlist.get(i).getCtime());
                    jo.put("username",userlist.get(i).getUsername());
                    jo.put("phone",userlist.get(i).getPhone());
                    jo.put("sex",userlist.get(i).getSex());
                    jo.put("address",userlist.get(i).getAddress());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                user.put(jo);
            }
            stat = "1";
        }

        JSONObject msg = new JSONObject();
        try {
            msg.put("stat",stat);
            msg.put("user",user);
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