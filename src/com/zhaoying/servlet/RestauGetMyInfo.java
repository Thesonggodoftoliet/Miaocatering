package com.zhaoying.servlet;

import com.zhaoying.instance.Restaurant;
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

@WebServlet("/api/RestauGetMyInfo")
public class RestauGetMyInfo extends HttpServlet {

    public RestauGetMyInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RestauGetMyInfo");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();

        int id=0;
        try {
            id= jsonObject.getInt("restaurantId");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GetInfoSE getInfoSE = new GetInfoSeImpl();
        Restaurant restaurant = getInfoSE.getRestaurantById(id);

        JSONObject object =new JSONObject();
        try{
            object.put("restaurantID",restaurant.getId());
            if (restaurant.getCover_url() == null)
                object.put("cover_url","");
            else
                object.put("cover_url",restaurant.getCover_url());
            object.put("restaurantname",restaurant.getRestauname());
            object.put("phone",restaurant.getPhone());
            object.put("address",restaurant.getAddress());
            object.put("license",restaurant.getLicence());
            object.put("passwd",restaurant.getPasswd());
            if (restaurant.getDescripion() == null)
                object.put("description",restaurant.getDescripion());
            else
                object.put("description",restaurant.getDescripion());

            if (restaurant.getSale_info() == null)
                object.put("sale_info","");
            else
                object.put("sale_info",restaurant.getSale_info());
            msg.put("restaurant",object);
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