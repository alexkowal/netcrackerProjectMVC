package com.myproject.netcracker.service;

import com.myproject.netcracker.domain.Advert;

import java.util.List;
import java.util.Set;

public interface AdvertService {


    void saveAdvert(Advert advert);



    List<Advert> findAllByOwnerId(Long id);

    Set<Advert> findAllByAddDateIsNotNull();
}
