package com.zhaoying.servlet;

import com.zhaoying.instance.Cuisine;
import com.zhaoying.instance.Order;
import com.zhaoying.instance.tempCui;
import com.zhaoying.service.GetInfoSE;
import com.zhaoying.service.UpdateCuiInfoSE;
import com.zhaoying.service.UpdateOrderInfoSE;
import com.zhaoying.service.impl.GetInfoSeImpl;
import com.zhaoying.service.impl.UpdateCuiInfoSEImpl;
import com.zhaoying.service.impl.UpdateOrderInfoSEImpl;
import com.zhaoying.utils.PraseUtils;
import com.zhaoying.utils.ReciveUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/RestauSetOrderFinished")
public class RestauSetOrderFinished extends HttpServlet {

    public RestauSetOrderFinished() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RestauSetOrderFinished");
        JSONObject jsonObject = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        PrintWriter out = response.getWriter();
        GetInfoSE getInfoSE = new GetInfoSeImpl();
        UpdateOrderInfoSE updateOrderInfoSE = new UpdateOrderInfoSEImpl();
        Order order = new Order();
        try {
            order = getInfoSE.getOrderById(jsonObject.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<tempCui> cuis = new ArrayList<>();
        try {
            cuis= PraseUtils.sTot(order.getCuisine_id());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //被售出
        UpdateCuiInfoSE updateCuiInfoSE =new UpdateCuiInfoSEImpl();
        for (int i = 0;i<cuis.size();i++)
            for (int j=0;j<cuis.get(i).getNum();j++)
                updateCuiInfoSE.beSold(cuis.get(i).getId());

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