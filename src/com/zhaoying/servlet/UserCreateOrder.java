package com.zhaoying.servlet;

import com.zhaoying.instance.Order;
import com.zhaoying.service.UpdateOrderInfoSE;
import com.zhaoying.service.impl.UpdateOrderInfoSEImpl;
import com.zhaoying.utils.PraseUtils;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/UserCreateOrder")
public class UserCreateOrder extends HttpServlet {

    public UserCreateOrder() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserCreateOrder");
        JSONObject jsonObject = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        PrintWriter out = response.getWriter();
        Order order = new Order();
        UpdateOrderInfoSE updateOrderInfoSE = new UpdateOrderInfoSEImpl();
        try {
            order.setOrder_status(0);
            order.setComment(jsonObject.getString("comment"));
            order.setBuyer_id(jsonObject.getInt("buyer_id"));
            order.setCuisine_id(jsonObject.getString("cuisine_id"));
            order.setRestau_id(jsonObject.getInt("restau_id"));
            order.setTot_price(updateOrderInfoSE.CaculatePrice(PraseUtils.tTOc(PraseUtils.sTot(jsonObject.getString("cuisine_id")))));
            order.setCtime(new Date().getTime());
            order.setAppoint_time(jsonObject.getLong("appoint_time"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int tag =updateOrderInfoSE.addOrder(order);
        try {
            if (tag != 0)
                msg.put("price",order.getTot_price());
            else
                msg.put("price",0);
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