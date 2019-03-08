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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/RestauGetAllOrder")
public class RestauGetAllOrder extends HttpServlet {

    public RestauGetAllOrder() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RestauGetAllOrder");
        JSONObject jsonObject = ReciveUtils.getObject(request);
        PrintWriter out = response.getWriter();
        Order order = new Order();

        try {
            order.setRestau_id(jsonObject.getInt("restau_id"));
            order.setOrder_status(jsonObject.getInt("type"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GetInfoSE getInfoSE = new GetInfoSeImpl();
        List<Order> orders = getInfoSE.getAllOrderByResId(order.getRestau_id());
        List<Order> orderList = new ArrayList<>();
        for (int i =0;i<orders.size();i++){
            if (orders.get(i).getOrder_status() == order.getOrder_status())
                orderList.add(orders.get(i));
        }

        JSONArray jsonArray = new JSONArray();
        try{
            for (int i = 0;i<orderList.size();i++){
                List<tempCui>cuis = PraseUtils.sTot(orderList.get(i).getCuisine_id());
                JSONObject object = new JSONObject();
                object.put("id",orderList.get(i).getId());
                object.put("ctime",orderList.get(i).getCtime());
                object.put("comment",orderList.get(i).getComment());
                object.put("tot_price",orderList.get(i).getTot_price());
                JSONArray cuisines = new JSONArray();
                for (int j =0;j<cuis.size();j++){
                    Cuisine cuisine = getInfoSE.getCuisineInfoById(cuis.get(j).getId());
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("cover_url",cuisine.getCover_url());
                    jsonObject1.put("id",cuis.get(j).getId());
                    jsonObject1.put("name",cuisine.getC_name());
                    jsonObject1.put("num",cuis.get(j).getNum());
                    cuisines.put(jsonObject1);
                }
                object.put("cuisine_id",cuisines);
                object.put("order_status",orderList.get(i).getOrder_status());
                object.put("appoint_time",orderList.get(i).getAppoint_time());
                jsonArray.put(object);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        JSONObject msg = new JSONObject();
        try {
            msg.put("order",jsonArray);
        } catch (JSONException e) {
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