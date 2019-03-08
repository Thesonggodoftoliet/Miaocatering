package com.zhaoying.servlet;

import com.zhaoying.instance.Order;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.service.UpdateOrderInfoSE;
import com.zhaoying.service.impl.GetInfoSeImpl;
import com.zhaoying.service.impl.UpdateOrderInfoSEImpl;
import com.zhaoying.utils.ReciveUtils;
import net.sf.json.JSON;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/RestauRefuseOrder")
public class RestauRefuseOrder extends HttpServlet {

    public RestauRefuseOrder() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RestauRefuseOrder");
        JSONObject  jsonObject = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        PrintWriter out = response.getWriter();
        Order order = new Order();
        GetInfoSE getInfoSE = new GetInfoSeImpl();
        UpdateOrderInfoSE updateOrderInfoSE = new UpdateOrderInfoSEImpl();
        try {
            order = getInfoSE.getOrderById(jsonObject.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        order.setOrder_status(3);

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