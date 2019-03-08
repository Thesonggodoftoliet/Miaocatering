package com.zhaoying.servlet;

import com.zhaoying.instance.Swiper;
import com.zhaoying.service.UpdateSwiInfoSE;
import com.zhaoying.service.impl.UpdateSwiInfoSEImpl;
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

@WebServlet("/api/AdminAddHomeImgs")
public class AdminAddHomeImgs extends HttpServlet {

    public AdminAddHomeImgs() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AdminAddHomeImgs");
        PrintWriter out = response.getWriter();
        JSONArray jsonArray = null;
        try {
            jsonArray = ReciveUtils.getArray(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject msg = new JSONObject();
        List<Swiper> swipers =new ArrayList<>();
        for (int i = 0;i<jsonArray.length();i++){
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                Swiper swiper =new Swiper();
                swiper.setUrl(object.getString("url"));
                swiper.setPosition(object.getInt("index"));
                System.out.println("index="+swiper.getPosition());
                swipers.add(swiper);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        UpdateSwiInfoSE updateSwiInfoSE = new UpdateSwiInfoSEImpl();
        int tag =0;
        for (int i = 0;i<swipers.size();i++){
            if (!updateSwiInfoSE.addSwiper(swipers.get(i))){
                tag =1;
                try {
                    msg.put("stat","0");
                    out.print(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        if (tag == 0) {
            try {
                msg.put("stat", "1");
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