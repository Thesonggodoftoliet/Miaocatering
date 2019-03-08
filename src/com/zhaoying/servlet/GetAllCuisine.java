package com.zhaoying.servlet;

import com.zhaoying.instance.Cuisine;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.service.impl.GetInfoSeImpl;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/GetAllCuisine")
public class GetAllCuisine extends HttpServlet {

    public GetAllCuisine() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/api/GetAllCuisine");

        JSONObject cuisineObj = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();

        PrintWriter out =response.getWriter();
        String extrasql = " order by ";
        try {
            extrasql = extrasql + cuisineObj.getString("method") ;//排序关键词
            extrasql =extrasql+ " "+cuisineObj.getString("way");//升序？降序？
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GetInfoSE gse = new GetInfoSeImpl();
        List<Cuisine> cuisine = gse.getAllCuisine(extrasql);
        JSONArray cuisines = new JSONArray();

        for (int i = 0; i < cuisine.size(); i++) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("id", cuisine.get(i).getId());
                jo.put("c_name", cuisine.get(i).getC_name());
                jo.put("price", cuisine.get(i).getPrice());
                if (cuisine.get(i).getCover_url() == null)
                    jo.put("cover_url","");
                else
                    jo.put("cover_url", cuisine.get(i).getCover_url());
                jo.put("origin_price", cuisine.get(i).getOrigin_price());
                jo.put("ctime", cuisine.get(i).getCtime());
                jo.put("restau_name", gse.getRestaurantById(cuisine.get(i).getRestau_id()).getRestauname());
                jo.put("tag", cuisine.get(i).getTag());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            cuisines.put(jo);
        }
        try {
            msg.put("cuisine",cuisines);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            if (cuisine.size() == 0)
                msg.put("stat","0");//失败
            else
                msg.put("stat","1");//成功

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