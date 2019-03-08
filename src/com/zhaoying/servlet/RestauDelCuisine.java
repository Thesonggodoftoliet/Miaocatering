package com.zhaoying.servlet;

import com.zhaoying.instance.Cuisine;
import com.zhaoying.service.UpdateCuiInfoSE;
import com.zhaoying.service.impl.UpdateCuiInfoSEImpl;
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

@WebServlet("/api/RestauDelCuisine")
public class RestauDelCuisine extends HttpServlet {

    public RestauDelCuisine() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RestauDelCuisine");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject= null;
        jsonObject = ReciveUtils.getObject(request);
        JSONObject msg = new JSONObject();
        UpdateCuiInfoSE updateCuiInfoSE = new UpdateCuiInfoSEImpl();

        List<Integer> list = null;
        try {
            list = PraseUtils.sToi(jsonObject.getString("cuisineID"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int tag = 0;
        for (int i= 0;i<list.size();i++)
            if(updateCuiInfoSE.deleteCuiById(list.get(i)).equals("0")){
                tag =1;
                try {
                    msg.put("stat","0");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                out.print(msg);
                break;
            }
        try {
            if (tag == 0) {
                msg.put("stat", "1");
                out.print(msg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("返回信息"+msg);
        out.flush();
        out.close();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}