package com.zhaoying.servlet;

import com.zhaoying.instance.Cuisine;
import com.zhaoying.instance.Restaurant;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.service.impl.GetInfoSeImpl;
import com.zhaoying.utils.PraseUtils;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/AdminGetResInfo")
public class AdminGetResInfo extends HttpServlet {

    public AdminGetResInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/api/AdminGetResInfo");
        PrintWriter out  = response.getWriter();
        JSONObject jsonObject = ReciveUtils.getObject(request);
        Restaurant restaurant = new Restaurant();
        GetInfoSE gse = new GetInfoSeImpl();
        try {
            restaurant = gse.getRestaurantById(jsonObject.getInt("restaurantID"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("从数据库获取的restaurant"+restaurant.getRestauname());
        List<Cuisine> cuisines;


        JSONObject msg  = new JSONObject();

        try{
            JSONObject jo = new JSONObject();
            jo.put("id", restaurant.getId());
            jo.put("restauname", restaurant.getRestauname());
            jo.put("address", restaurant.getAddress());
            jo.put("phone", restaurant.getPhone());
            cuisines=gse.getCuisineByResId(restaurant.getId());
            JSONArray cuisine = new JSONArray();
            for (int i = 0; i < cuisines.size(); i++) {
                JSONObject object = new JSONObject();
                object.put("id", cuisines.get(i).getId());
                object.put("c_name", cuisines.get(i).getC_name());
                object.put("price", cuisines.get(i).getPrice());
                if (cuisines.get(i).getCover_url() ==null)
                    object.put("cover_url","");
                else
                    object.put("cover_url", cuisines.get(i).getCover_url());
                object.put("origin_price", cuisines.get(i).getOrigin_price());
                object.put("ctime", cuisines.get(i).getCtime());
                object.put("tag", cuisines.get(i).getTag());
                cuisine.put(object);
            }
            jo.put("cuisinelist", cuisine);

            if (restaurant.getCover_url() == null)
                jo.put("cover_url","");
            else
                jo.put("cover_url",restaurant.getCover_url());
            jo.put("ctime", restaurant.getCtime());
            jo.put("collect_num", restaurant.getCollect_num());
            if (restaurant.getDescripion() == null)
                jo.put("description","");
            else
                jo.put("description", restaurant.getDescripion());
            jo.put("licence", restaurant.getLicence());
            if (restaurant.getSale_info() == null)
                jo.put("sale_info","");
            else
                jo.put("sale_info",restaurant.getSale_info());
            msg.put("restaurant",jo);
        }catch (JSONException e){
            e.printStackTrace();
        }
        try {
            System.out.println("restaurant"+msg.getString("restaurant"));
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