package com.myproject.netcracker.service;

import com.myproject.netcracker.domain.Advert;

import java.util.List;

public interface AdvertService {

    Advert findByOwnerId(Long id);

    void saveAdvert(Advert advert);

    List<Advert> findAllByAddDateIsNotNull();
}
