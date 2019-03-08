package com.zhaoying.servlet;

import com.zhaoying.instance.User;
import com.zhaoying.service.UpdateCuiInfoSE;
import com.zhaoying.service.UpdateUserInfoSE;
import com.zhaoying.service.impl.UpdateCuiInfoSEImpl;
import com.zhaoying.service.impl.UpdateUserInfoSEImpl;
import com.zhaoying.utils.PraseUtils;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/UserCollectCuisine")
public class UserCollectCuisine extends HttpServlet {

    public UserCollectCuisine() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/api/UserCollectCuisine");
        JSONObject cuisineObj = ReciveUtils.getObject(request);

        PrintWriter out =response.getWriter();
        User tuser = new User();
        try {
            tuser.setId(cuisineObj.getInt("UserId"));
            tuser.setCuisine_collected(cuisineObj.getString("cuisineID"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        UpdateUserInfoSE use= new UpdateUserInfoSEImpl();
        UpdateCuiInfoSE cuiInfoSE = new UpdateCuiInfoSEImpl();
        cuiInfoSE.beCollected(PraseUtils.sToi(tuser.getCuisine_collected()).get(0));
        String tag = use.collectCuisine(tuser);

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