package com.zhaoying.servlet;

import com.zhaoying.instance.Restaurant;
import com.zhaoying.service.UpdateResInfoSE;
import com.zhaoying.service.impl.UpdateResInfoSEImpl;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/RestauSetInfo")
public class RestauSetInfo extends HttpServlet {

    public RestauSetInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RestauSetInfo");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();

        Restaurant restaurant =new Restaurant();
        try{
            restaurant.setId(jsonObject.getInt("restaurantID"));
            restaurant.setSale_info(jsonObject.getString("sale_info"));
            restaurant.setCover_url(jsonObject.getString("cover_url"));
            restaurant.setPhone(jsonObject.getString("phone"));
            restaurant.setDescripion(jsonObject.getString("description"));
            restaurant.setPasswd(jsonObject.getString("passwd"));
            restaurant.setRestauname(jsonObject.getString("restaurantname"));
            restaurant.setAddress(jsonObject.getString("address"));
        }catch (JSONException e){
            e.printStackTrace();
        }

        UpdateResInfoSE updateResInfoSE = new UpdateResInfoSEImpl();
        String tag = updateResInfoSE.setRestaurant(restaurant);
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