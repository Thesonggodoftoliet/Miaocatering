package com.zhaoying.instance;

public class Order {
    private int id;
    private String cuisine_id;
    private int restau_id;
    private long ctime;
    private String comment;
    private int buyer_id;
    private int order_status;
    private int tot_price;
    private long appoint_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuisine_id() {
        return cuisine_id;
    }

    public void setCuisine_id(String cuisine_id) {
        this.cuisine_id = cuisine_id;
    }

    public int getRestau_id() {
        return restau_id;
    }

    public void setRestau_id(int restau_id) {
        this.restau_id = restau_id;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public int getTot_price() {
        return tot_price;
    }

    public void setTot_price(int tot_price) {
        this.tot_price = tot_price;
    }

    public long getAppoint_time() {
        return appoint_time;
    }

    public void setAppoint_time(long appoint_time) {
        this.appoint_time = appoint_time;
    }
}
