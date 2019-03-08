package com.zhaoying.servlet;

import com.zhaoying.instance.Cuisine;
import com.zhaoying.instance.Restaurant;
import com.zhaoying.instance.User;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.service.impl.GetInfoSeImpl;
import com.zhaoying.utils.PraseUtils;
import com.zhaoying.utils.ReciveUtils;
import net.sf.json.JSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/GetCollect")
public class GetCollect extends HttpServlet {

    public GetCollect() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/api/GetCollect");
        JSONObject msg = new JSONObject();
        PrintWriter out = response.getWriter();
        JSONObject object = ReciveUtils.getObject(request);
        GetInfoSE gse = new GetInfoSeImpl();
        User user = new User();
        try {
            user = gse.getUserInfoById(object.getInt("UserId"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<Restaurant> restaurants;
        List<Cuisine> cuisines;

        if (user.getRestaurant_collected() == null) {
            try {
                msg.put("restaurant","");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            JSONArray restaurant = new JSONArray();
            restaurants = PraseUtils.iTor(PraseUtils.sToi(user.getRestaurant_collected()));
            try {
                for (int i =0;i<restaurants.size();i++) {
                    JSONObject jo = new JSONObject();
                    jo.put("id", restaurants.get(i).getId());
                    jo.put("restaurantname", restaurants.get(i).getRestauname());
                    jo.put("address", restaurants.get(i).getAddress());
                    if (restaurants.get(i).getCover_url() == null)
                        jo.put("cover_url","");
                    else
                        jo.put("cover_url",restaurants.get(i).getCover_url());
                    restaurant.put(jo);
                }
                msg.put("restaurant",restaurant);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            if (user.getCuisine_collected() == null)
                msg.put("cuisine","");
            else{
                JSONArray cuisine = new JSONArray();
                cuisines = PraseUtils.iToc(PraseUtils.sToi(user.getCuisine_collected()));
                for (int i = 0;i<cuisines.size();i++){
                    JSONObject jo = new JSONObject();
                    jo.put("id",cuisines.get(i).getId());
                    jo.put("c_name",cuisines.get(i).getC_name());
                    jo.put("price",cuisines.get(i).getPrice());
                    if (cuisines.get(i).getCover_url() == null)
                        jo.put("cover_url","");
                    else
                        jo.put("cover_url",cuisines.get(i).getCover_url());
                    jo.put("origin_price",cuisines.get(i).getOrigin_price());
                    jo.put("ctime",cuisines.get(i).getCtime());
                    jo.put("restau_name",gse.getRestaurantById(cuisines.get(i).getRestau_id()).getRestauname());
                    jo.put("tag",cuisines.get(i).getTag());
                    cuisine.put(jo);
                }
                msg.put("cuisine",cuisine);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }


        System.out.println("msg = "+msg);

        out.print(msg);
        out.flush();
        out.close();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}