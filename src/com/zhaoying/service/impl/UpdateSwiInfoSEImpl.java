package com.zhaoying.service.impl;

import com.zhaoying.dao.DaoImpl.SwiperDaoImpl;
import com.zhaoying.dao.SwiperDao;
import com.zhaoying.instance.Swiper;
import com.zhaoying.service.UpdateSwiInfoSE;

import java.util.List;

public class UpdateSwiInfoSEImpl implements UpdateSwiInfoSE {
    @Override
    public boolean addSwiper(Swiper swiper) {
        SwiperDao swiperDao = new SwiperDaoImpl();
        List<Swiper> swipers = swiperDao.getAllSwiper();
        if (swipers.size() == 0)
            swiper.setId(swiperDao.getNumOfSwiper()+1);
        else
            swiper.setId(swipers.get(swipers.size()-1).getId()+1);

        if (swiperDao.getSwiperByIndex(swiper.getPosition()) == null) {
            System.out.println("indedx="+swiper.getPosition());
            if (swiperDao.addSwiper(swiper) == null)
                return false;
            else
                return true;
        }else {
            if (!swiperDao.updateSwiByIndex(swiper))
                return false;
            else
                return true;
        }
    }

    @Override
    public String setSwiper(Swiper swiper) {
        SwiperDao swiperDao =new SwiperDaoImpl();
        Swiper sqlswiper = swiperDao.getSwiperByIndex(swiper.getPosition());
        if (sqlswiper == null)
            return "-1";
        else {
            if (swiper.getDescription() != null)
                if (sqlswiper.getDescription() == null)
                    sqlswiper.setDescription(swiper.getDescription());
                else if ( !sqlswiper.getDescription().equals(swiper.getDescription()))
                    sqlswiper.setDescription(swiper.getDescription());
            if (swiper.getPosition() != sqlswiper.getPosition() && swiper.getPosition()!=0)
                sqlswiper.setPosition(swiper.getPosition());
            if (swiper.getUrl()!=null)
                if (sqlswiper.getUrl() == null)
                    sqlswiper.setUrl(swiper.getUrl());
                else if (!sqlswiper.getUrl().equals(swiper.getUrl()))
                    sqlswiper.setUrl(swiper.getUrl());
        }
        if (swiperDao.updateSwiByIndex(sqlswiper))
            return "1";
        else
            return "0";
    }
}
