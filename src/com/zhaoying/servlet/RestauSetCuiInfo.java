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

@WebServlet("/api/RestauSetCuiInfo")
public class RestauSetCuiInfo extends HttpServlet {

    public RestauSetCuiInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RestauSetCuiInfo");
        PrintWriter out  = response.getWriter();
        JSONObject cuisine = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        Cuisine newone = new Cuisine();

        try{
            newone.setId(cuisine.getInt("CuisineId"));
            newone.setTag(cuisine.getString("tag"));
            newone.setDetail_url(cuisine.getString("detail_url"));
            newone.setCover_url(cuisine.getString("cover_url"));
            newone.setOrigin_price(cuisine.getInt("origin_price"));
            newone.setPrice(cuisine.getInt("price"));
            newone.setC_name(cuisine.getString("c_name"));
        }catch (JSONException e){
            e.printStackTrace();
        }
        UpdateCuiInfoSE updateCuiInfoSE =new UpdateCuiInfoSEImpl();
        String  id = updateCuiInfoSE.setCuisine(newone);
        try {
            if (id.equals("0")) {
                msg.put("stat", "0");
            }else {
                msg.put("stat","1");
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