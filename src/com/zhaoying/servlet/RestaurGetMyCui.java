package com.zhaoying.servlet;

import com.zhaoying.instance.Cuisine;
import com.zhaoying.instance.Restaurant;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.service.impl.GetInfoSeImpl;
import com.zhaoying.utils.PraseUtils;
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

@WebServlet("/api/RestaurGetMyCui")
public class RestaurGetMyCui extends HttpServlet {

    public RestaurGetMyCui() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/api/RestaurGetMyCui");
        PrintWriter out  = response.getWriter();
        JSONObject object = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        Restaurant restaurant = null;
        GetInfoSE gse = new GetInfoSeImpl();
        try {
            restaurant = gse.getRestaurantById(object.getInt("restaurantID"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<Cuisine> cuisines = gse.getCuisineByResId(restaurant.getId());
        try {
            JSONArray cuisine = new JSONArray();
            for (int i =0;i<cuisines.size();i++){
                JSONObject jo = new JSONObject();
                jo.put("id", cuisines.get(i).getId());
                jo.put("c_name", cuisines.get(i).getC_name());
                jo.put("price", cuisines.get(i).getPrice());
                if (cuisines.get(i).getCover_url() == null)
                    jo.put("cover_url","");
                else
                    jo.put("cover_url", cuisines.get(i).getCover_url());
                jo.put("origin_price", cuisines.get(i).getOrigin_price());
                jo.put("ctime", cuisines.get(i).getCtime());
                jo.put("tag", cuisines.get(i).getTag());
                cuisine.put(jo);
                }
                System.out.println("返回信息"+cuisine);
                msg.put("cuisine",cuisine);
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