package com.zhaoying.servlet;

import com.zhaoying.instance.Swiper;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.service.impl.GetInfoSeImpl;
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

@WebServlet("/api/GetHomeSwipers")
public class GetHomeSwipers extends HttpServlet {

    public GetHomeSwipers() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GetHomeSwipers");
        PrintWriter out = response.getWriter();
        JSONObject msg = new JSONObject();
        GetInfoSE getInfoSE = new GetInfoSeImpl();
        List<Swiper> swipers = getInfoSE.getAllSwiper();
        JSONArray swiper = new JSONArray();
        try {

            for (int i = 0;i<swipers.size();i++){
                JSONObject jo = new JSONObject();
                jo.put("id",swipers.get(i).getId());
                jo.put("url",swipers.get(i).getUrl());
                jo.put("index",swipers.get(i).getPosition());
                swiper.put(jo);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        try{
                msg.put("swiper",swiper);
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