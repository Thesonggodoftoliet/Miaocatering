package com.zhaoying.instance;

public class User {
    private int id;
    private  String username;
    private  String phone;
    private  String address;
    private int sex;
    private long ctime;
    private String passwd;
    private String cuisine_collected;
    private String restaurant_collected;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getCuisine_collected() {
        return cuisine_collected;
    }

    public void setCuisine_collected(String cuisine_collected) {
        this.cuisine_collected = cuisine_collected;
    }

    public String getRestaurant_collected() {
        return restaurant_collected;
    }

    public void setRestaurant_collected(String restaurant_collected) {
        this.restaurant_collected = restaurant_collected;
    }
}
