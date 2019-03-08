package com.zhaoying.servlet;

import com.zhaoying.instance.Order;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.service.UpdateOrderInfoSE;
import com.zhaoying.service.impl.GetInfoSeImpl;
import com.zhaoying.service.impl.UpdateOrderInfoSEImpl;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/RestauReceiveOrder")
public class RestauReceiveOrder extends HttpServlet {

    public RestauReceiveOrder() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RestauReceiveOrder");
        JSONObject msg  = new JSONObject();
        JSONObject object = ReciveUtils.getObject(request);
        PrintWriter out = response.getWriter();
        int orderid = 0;
        try {
            orderid = object.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        UpdateOrderInfoSE updateOrderInfoSE = new UpdateOrderInfoSEImpl();
        GetInfoSE getInfoSE = new GetInfoSeImpl();
        Order order = getInfoSE.getOrderById(orderid);
        String tag = updateOrderInfoSE.changeOrderStatu(order);
        try {
            msg.put("stat",tag);
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