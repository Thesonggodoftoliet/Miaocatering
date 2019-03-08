package com.zhaoying.servlet;

import com.zhaoying.instance.User;
import com.zhaoying.service.UpdateResInfoSE;
import com.zhaoying.service.UpdateUserInfoSE;
import com.zhaoying.service.impl.UpdateResInfoSEImpl;
import com.zhaoying.service.impl.UpdateUserInfoSEImpl;
import com.zhaoying.utils.PraseUtils;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/UserCollectRestaurant")
public class UserCollectRestaurant extends HttpServlet {

    public UserCollectRestaurant() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/api/UserCollectRestaurant");
        JSONObject cuisineObj = ReciveUtils.getObject(request);
        PrintWriter out =response.getWriter();
        User tuser = new User();
        try {
            tuser.setId(cuisineObj.getInt("UserId"));
            tuser.setRestaurant_collected(cuisineObj.getString("restauID"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        UpdateUserInfoSE use= new UpdateUserInfoSEImpl();
        UpdateResInfoSE resInfoSE = new UpdateResInfoSEImpl();
        resInfoSE.beCollected(PraseUtils.sToi(tuser.getRestaurant_collected()).get(0));
        String tag = use.collectRestaurant(tuser);

        JSONObject msg= new JSONObject();
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