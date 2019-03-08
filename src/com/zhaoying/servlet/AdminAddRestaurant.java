package com.zhaoying.servlet;

import com.zhaoying.instance.Restaurant;
import com.zhaoying.service.RegisterSE;
import com.zhaoying.service.impl.RegisterSEImpl;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/AdminAddRestaurant")
public class AdminAddRestaurant extends HttpServlet {

    public AdminAddRestaurant() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = ReciveUtils.getObject(request);
        Restaurant restaurant =new Restaurant();
        try {
            restaurant.setPasswd(jsonObject.getString("passwd"));
            restaurant.setPhone(jsonObject.getString("phone"));
            restaurant.setAddress(jsonObject.getString("address"));
            restaurant.setRestauname(jsonObject.getString("restauname"));
            restaurant.setCtime(System.currentTimeMillis());
            restaurant.setLicence(jsonObject.getString("licence"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RegisterSE rse = new RegisterSEImpl();
        int tag = rse.addRestaurant(restaurant);
        JSONObject msg = new JSONObject();
        try {
            msg.put("restaurantID",tag);
            if (tag == 0)
                msg.put("stat","0");
            else
                msg.put("stat","1");
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