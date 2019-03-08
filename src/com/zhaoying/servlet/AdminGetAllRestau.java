package com.zhaoying.servlet;

import com.zhaoying.instance.Restaurant;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.service.impl.GetInfoSeImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/AdminGetAllRestau")
public class AdminGetAllRestau extends HttpServlet {

    public AdminGetAllRestau() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/api/AdminGetAllRestau");
        PrintWriter out = response.getWriter();
        List<Restaurant> restaurants = null;
        GetInfoSE gse = new GetInfoSeImpl();
        restaurants = gse.getAllRestaurant("");
        JSONArray restaurant = new JSONArray();
        for (int i =0;i<restaurants.size();i++){
            JSONObject jo = new JSONObject();
            try {
                jo.put("id",restaurants.get(i).getId());
                jo.put("restaurantname",restaurants.get(i).getRestauname());
                jo.put("address",restaurants.get(i).getAddress());
                jo.put("ctime",restaurants.get(i).getCtime());
                restaurant.put(jo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        JSONObject msg = new JSONObject();
        try {
            msg.put("restaurant",restaurant);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("restaurant"+restaurant);

        out.print(msg);
        out.flush();
        out.close();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}