package com.zhaoying.servlet;

import com.zhaoying.instance.Cuisine;
import com.zhaoying.service.UpdateCuiInfoSE;
import com.zhaoying.service.impl.UpdateCuiInfoSEImpl;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/RestauAddCuisine")
public class RestauAddCuisine extends HttpServlet {

    public RestauAddCuisine() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RestauAddCuisine");
        PrintWriter out = response.getWriter();
        JSONObject cuisine = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();

        Cuisine newcuisine = new Cuisine();

        try{
            newcuisine.setRestau_id(cuisine.getInt("restau_id"));
            newcuisine.setPrice(cuisine.getInt("price"));
            newcuisine.setC_name(cuisine.getString("c_name"));
            newcuisine.setOrigin_price(cuisine.getInt("origin_price"));
            newcuisine.setCtime(System.currentTimeMillis());
            newcuisine.setCover_url(cuisine.getString("cover_url"));
            newcuisine.setDetail_url(cuisine.getString("detail_url"));
            newcuisine.setTag(cuisine.getString("tag"));
        }catch (JSONException e){
            e.printStackTrace();
        }

        UpdateCuiInfoSE updateCuiInfoSE =new UpdateCuiInfoSEImpl();
        int id = updateCuiInfoSE.addCuisine(newcuisine);
        try {
            if (id == 0) {
                msg.put("stat", "0");
            }else {
                msg.put("stat","1");
                msg.put("CuisineId",id);
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