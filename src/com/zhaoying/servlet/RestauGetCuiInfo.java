package com.zhaoying.servlet;

import com.zhaoying.instance.Cuisine;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.service.impl.GetInfoSeImpl;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/RestauGetCuiInfo")
public class RestauGetCuiInfo extends HttpServlet {

    public RestauGetCuiInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RestauGetCuiInfo");
        JSONObject object = ReciveUtils.getObject(request);
        PrintWriter out = response.getWriter();
        Cuisine cuisine =null;
        GetInfoSE getInfoSE = new GetInfoSeImpl();

        try {
            cuisine = getInfoSE.getCuisineInfoById(object.getInt("CuisineId"));
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
            jsonObject.put("ctime",cuisine.getCtime());
            jsonObject.put("tag",cuisine.getTag());

        }catch (JSONException e){
            e.printStackTrace();
        }
        JSONObject msg = new JSONObject();
        try{
            if (cuisine == null){
                msg.put("stat","0");
                msg.put("cuisine","");
            }else {
                msg.put("stat","1");
                msg.put("cuisine",jsonObject);
            }
        }catch (JSONException e){
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