package com.zhaoying.servlet;

import com.zhaoying.instance.Cuisine;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.service.impl.GetInfoSeImpl;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/GetCuisineInfo")
public class GetCuisineInfo extends HttpServlet {

    public GetCuisineInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/api/GetCuisineInfo");
        JSONObject userObj = ReciveUtils.getObject(request);

        PrintWriter out =response.getWriter();
        Cuisine cuisine = null;
        GetInfoSE gse = new GetInfoSeImpl();
        try {
            cuisine = gse.getCuisineInfoById(userObj.getInt("CuisineId"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id",cuisine.getId());
            jsonObject.put("c_name",cuisine.getC_name());
            jsonObject.put("price",cuisine.getPrice());
            if (cuisine.getCover_url() == null)
                jsonObject.put("cover_url","");
            else
                jsonObject.put("cover_url",cuisine.getCover_url());
            if (cuisine.getDetail_url() ==null)
                jsonObject.put("detail_url","");
            else
                jsonObject.put("detail_url",cuisine.getDetail_url());
            jsonObject.put("origin_price",cuisine.getOrigin_price());
            jsonObject.put("sell_num",cuisine.getSell_num());
            jsonObject.put("collect_num",cuisine.getCollect_num());
            jsonObject.put("restau_id",cuisine.getRestau_id());
            jsonObject.put("restau_name",gse.getRestaurantById(cuisine.getRestau_id()).getRestauname());
            jsonObject.put("ctime",cuisine.getCtime());
            jsonObject.put("tag",cuisine.getTag());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject msg = new JSONObject();

        try {
            if (cuisine == null){
                msg.put("stat","0");
            }
            else
                msg.put("stat","1");
                msg.put("cuisine",jsonObject);
            } catch (JSONException e) {

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