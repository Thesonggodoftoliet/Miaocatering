package com.zhaoying.dao;

import com.zhaoying.instance.Swiper;

import java.util.List;

public interface SwiperDao {
    Swiper getSwiperByIndex(int index);
    List<Swiper> getAllSwiper();
    Swiper addSwiper(Swiper swiper);
    boolean updateSwiByIndex(Swiper swiper);
    boolean deleteSwiById(int id);
    int getNumOfSwiper();
}
