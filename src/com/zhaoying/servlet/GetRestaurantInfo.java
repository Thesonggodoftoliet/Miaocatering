package com.zhaoying.servlet;

import com.zhaoying.instance.Cuisine;
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

@WebServlet("/api/GetRestaurantInfo")
public class GetRestaurantInfo extends HttpServlet {

    public GetRestaurantInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/api/GetRestaurantInfo");
        JSONObject userObj = ReciveUtils.getObject(request);

        PrintWriter out =response.getWriter();
        Restaurant restaurant = null;
        GetInfoSE gse = new GetInfoSeImpl();
        try {
            restaurant = gse.getRestaurantById(userObj.getInt("RestaurantId"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject msg = new JSONObject();

        if (restaurant != null) {
            try {
                msg.put("stat","1");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //放入餐厅信息
            JSONObject jo = new JSONObject();
            try {
                //放入菜品信息
                List<Cuisine> cuisines = gse.getCuisineByResId(restaurant.getId());
                JSONArray cuisine = new JSONArray();
                for (int i = 0; i < cuisines.size(); i++) {
                    JSONObject object = new JSONObject();
                    object.put("id", cuisines.get(i).getId());
                    object.put("c_name", cuisines.get(i).getC_name());
                    object.put("price", cuisines.get(i).getPrice());
                    if (cuisines.get(i).getCover_url() == null)
                        object.put("cover_url", "");
                    else
                        object.put("cover_url", cuisines.get(i).getCover_url());
                    object.put("origin_price", cuisines.get(i).getOrigin_price());
                    object.put("ctime", cuisines.get(i).getCtime());
                    object.put("tag", cuisines.get(i).getTag());
                    cuisine.put(object);
                }
                jo.put("cuisinelist",cuisine);
                jo.put("id", restaurant.getId());
                jo.put("restauname", restaurant.getRestauname());
                jo.put("address", restaurant.getAddress());
                jo.put("phone", restaurant.getPhone());

                if (restaurant.getCover_url() == null)
                    jo.put("cover_url","");
                else
                    jo.put("cover_url",restaurant.getCover_url());
                System.out.println("cover_url");
                jo.put("ctime", restaurant.getCtime());
                jo.put("collect_num", restaurant.getCollect_num());
                System.out.println("ctime + collect_num");
                if (restaurant.getDescripion() == null)
                    jo.put("description","");
                else
                    jo.put("description", restaurant.getDescripion());
                System.out.println("description");
                jo.put("licence", restaurant.getLicence());
                System.out.println("license");
                if (restaurant.getSale_info() == null){
                    System.out.println("if"+restaurant.getSale_info());
                    jo.put("sale_info","");
                } else{
                    System.out.println("else"+restaurant.getSale_info());
                    jo.put("sale_info",restaurant.getSale_info());
                }
                System.out.println("准备放入msg"+jo);
                msg.put("restaurant",jo);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            try {
                msg.put("stat","0");//fail
            } catch (JSONException e) {
                e.printStackTrace();
            }
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