package com.zhaoying.instance;

public class Restaurant {
    private int id;
    private String restauname;
    private String address;
    private String phone;
    private String passwd;
    private long ctime;
    private int collect_num;
    private String descripion;
    private String licence;
    private String cover_url;
    private String sale_info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestauname() {
        return restauname;
    }

    public void setRestauname(String restauname) {
        this.restauname = restauname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public int getCollect_num() {
        return collect_num;
    }

    public void setCollect_num(int collect_num) {
        this.collect_num = collect_num;
    }

    public String getDescripion() {
        return descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getSale_info() {
        return sale_info;
    }

    public void setSale_info(String sale_info) {
        this.sale_info = sale_info;
    }
}
