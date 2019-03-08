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

@WebServlet("/api/RestauGetOrderInfo")
public class RestauGetOrderInfo extends HttpServlet {

    public RestauGetOrderInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RestauGetOrderInfo");
        JSONObject jsonObject = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        PrintWriter out = response.getWriter();

        Order order;
        GetInfoSE getInfoSE = new GetInfoSeImpl();
        JSONObject object = new JSONObject();
        try {
            order = getInfoSE.getOrderById(jsonObject.getInt("id"));
            List<tempCui> cuis = PraseUtils.sTot(order.getCuisine_id());
            object.put("id",order.getId());
            object.put("ctime",order.getCtime());
            object.put("comment",order.getComment());
            object.put("tot_price",order.getTot_price());
            JSONArray cuisines = new JSONArray();
            for (int j =0;j<cuis.size();j++){
                Cuisine cuisine =getInfoSE.getCuisineInfoById(cuis.get(j).getId());
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("cover_url",cuisine.getCover_url());
                jsonObject1.put("id",cuis.get(j).getId());
                jsonObject1.put("name",cuisine.getC_name());
                jsonObject1.put("num",cuis.get(j).getNum());
                cuisines.put(jsonObject1);
            }
            object.put("user_id",order.getBuyer_id());
            object.put("user_name",getInfoSE.getUserInfoById(order.getBuyer_id()).getUsername());
            object.put("phone",getInfoSE.getUserInfoById(order.getBuyer_id()).getPhone());
            object.put("cuisine_id",cuisines);
            object.put("order_status",order.getOrder_status());
            object.put("appoint_time",order.getAppoint_time());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            msg.put("order",object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("返回信息"+object);

        out.print(msg);
        out.flush();
        out.close();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}