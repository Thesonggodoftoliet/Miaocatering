package com.zhaoying.servlet;

import com.zhaoying.instance.Cuisine;
import com.zhaoying.instance.Order;
import com.zhaoying.instance.tempCui;
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

@WebServlet("/api/UserGetMyOrder")
public class UserGetMyOrder extends HttpServlet {

    public UserGetMyOrder() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserGetMyOrder");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        int id = 0;
        try {
            id = jsonObject.getInt("UserId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GetInfoSE getInfoSE = new GetInfoSeImpl();
        List<Order> orders = getInfoSE.getAllOrderByUserId(id);

        JSONArray order = new JSONArray();
        try {
            for (int i = 0; i < orders.size(); i++) {
                List<tempCui> cuis = PraseUtils.sTot(orders.get(i).getCuisine_id());

                JSONObject jo = new JSONObject();//jo 单个订单信息
                jo.put("id",orders.get(i).getId());
                jo.put("ctime",orders.get(i).getCtime());
                jo.put("comment",orders.get(i).getComment());
                jo.put("tot_price",orders.get(i).getTot_price());
                JSONArray cuisines = new JSONArray();//菜品信息

                for (int j =0;j<cuis.size();j++){
                    Cuisine cuisine =getInfoSE.getCuisineInfoById(cuis.get(j).getId());
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("cover_url",cuisine.getCover_url());
                    jsonObject1.put("id",cuisine.getId());
                    jsonObject1.put("name",cuisine.getC_name());
                    jsonObject1.put("num",cuis.get(j).getNum());
                    cuisines.put(jsonObject1);
                    System.out.println("单个订单菜品信息 "+cuisines);
                }

                jo.put("cuisine_id",cuisines);
                jo.put("restau_id",orders.get(i).getRestau_id());
                jo.put("restau_name",getInfoSE.getRestaurantById(orders.get(i).getRestau_id()).getRestauname());
                jo.put("order_staus",orders.get(i).getOrder_status());
                jo.put("appoint_time",orders.get(i).getAppoint_time());
                order.put(jo);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        try {
            msg.put("order",order);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("返回信息"+order);
        out.print(msg);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}