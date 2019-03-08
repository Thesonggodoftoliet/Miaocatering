package com.zhaoying.servlet;

import com.zhaoying.instance.Restaurant;
import com.zhaoying.service.LoginSE;
import com.zhaoying.service.impl.LoginSEImpl;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/RestaurantLogin")
public class RestaurantLogin extends HttpServlet {

    public RestaurantLogin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream js = request.getInputStream();
        InputStreamReader jsr = new InputStreamReader(js);
        BufferedReader br = new BufferedReader(jsr);

        String restaurantjson = br.readLine();
        System.out.println("有东西吗"+restaurantjson);


        JSONObject restaurantObj = null;

        try {
            restaurantObj = new JSONObject(restaurantjson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        PrintWriter out =response.getWriter();
        Restaurant restaurant = new Restaurant();
        try {
            restaurant.setPhone(restaurantObj.getString("phone"));
            restaurant.setPasswd(restaurantObj.getString("passwd"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LoginSE lse = new LoginSEImpl();
        String tag = lse.retaurantCheck(restaurant);

        System.out.println("tag = "+tag);

        JSONObject jo = new JSONObject();
        if (!(tag.equals("-1")||tag.equals("0"))){
            String userid = tag;
            tag = "1";
            System.out.println(tag);
            try {
                jo.put("RestaurantId",userid);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                jo.put("RestaurantId","0");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            jo.put("stat",tag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("返回信息"+jo);
        out.print(jo);
        out.flush();
        out.close();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}