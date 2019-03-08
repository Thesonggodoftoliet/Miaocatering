package com.zhaoying.servlet;

import com.zhaoying.instance.Restaurant;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.service.impl.GetInfoSeImpl;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/GetAllRestaurant")
public class GetAllRestaurant extends HttpServlet {

    public GetAllRestaurant() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/api/GetAllRestaurant");

        JSONObject cuisineObj = ReciveUtils.getObject(request);

        PrintWriter out =response.getWriter();
        String extrasql = " order by ";
        try {
            extrasql = extrasql + cuisineObj.getString("method");
            extrasql =extrasql+ " "+cuisineObj.getString("way");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GetInfoSE gse = new GetInfoSeImpl();
        List<Restaurant> restaurants;
        restaurants = gse.getAllRestaurant(extrasql);
        JSONArray restaurant = new JSONArray();

        for (int i =0;i<restaurants.size();i++){
            JSONObject jo = new JSONObject();
            try {
                jo.put("id",restaurants.get(i).getId());
                jo.put("restaurantname",restaurants.get(i).getRestauname());
                jo.put("address",restaurants.get(i).getAddress());
                if (restaurants.get(i).getCover_url() == null)
                    jo.put("cover_url","");
                else
                    jo.put("cover_url",restaurants.get(i).getCover_url());
                restaurant.put(jo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONObject msg = new JSONObject();
        try {
            msg.put("restaurant",restaurant);
            System.out.println("restaurant"+restaurant);
            if (restaurants.size() == 0){
                msg.put("stat","0");//faile
            }
            else {
                msg.put("stat","1");//success
            }
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