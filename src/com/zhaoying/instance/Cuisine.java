package com.zhaoying.instance;

public class Cuisine {
    private int id;
    private String c_name;
    private int price;
    private String cover_url;
    private String detail_url;
    private int origin_price;
    private int sell_num;
    private int collect_num;
    private int restau_id;
    private long ctime;
    private String tag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getDetail_url() {
        return detail_url;
    }

    public void setDetail_url(String detail_url) {
        this.detail_url = detail_url;
    }

    public int getOrigin_price() {
        return origin_price;
    }

    public void setOrigin_price(int origin_price) {
        this.origin_price = origin_price;
    }

    public int getSell_num() {
        return sell_num;
    }

    public void setSell_num(int sell_num) {
        this.sell_num = sell_num;
    }

    public int getCollect_num() {
        return collect_num;
    }

    public void setCollect_num(int collect_num) {
        this.collect_num = collect_num;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
