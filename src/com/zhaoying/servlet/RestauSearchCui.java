package com.zhaoying.servlet;

import com.zhaoying.instance.Cuisine;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.service.impl.GetInfoSeImpl;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/RestauSearchCui")
public class RestauSearchCui extends HttpServlet {

    public RestauSearchCui() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RestauSearchCui");
        PrintWriter out = response.getWriter();
        JSONObject object = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        List<Cuisine> cuisine;
        String cui_name =null;
        int restau_id =0;
        try {
            cui_name = object.getString("c_name");
            restau_id = object.getInt("restaurantID");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GetInfoSE getInfoSE = new GetInfoSeImpl();
        if (getInfoSE.getCuisineByName(restau_id,cui_name) == null){
            try{
                msg.put("stat",0);
                msg.put("cuisine","");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            JSONArray cuisines = new JSONArray();
            cuisine = getInfoSE.getCuisineByName(restau_id,cui_name);
            for (int i = 0;i<cuisine.size();i++){
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
                    jo.put("tag", cuisine.get(i).getTag());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                cuisines.put(jo);
            }
            try{
                msg.put("cuisine",cuisines);
                msg.put("stat","1");
            }catch (JSONException e){
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