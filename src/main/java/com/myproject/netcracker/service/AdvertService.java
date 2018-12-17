package com.myproject.netcracker.service;

import com.myproject.netcracker.domain.Advert;
import com.myproject.netcracker.domain.Brand;

import java.util.List;

public interface AdvertService {

    Advert findByOwnerId(Long id);

    void saveAdvert(Advert advert);



   // List<Advert> findAllByOwnerId(Long id);

    List<Advert> findAllByAddDateIsNotNull();
}
