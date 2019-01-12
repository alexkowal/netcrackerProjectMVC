package com.myproject.netcracker.service;

import com.myproject.netcracker.domain.Advert;
import com.myproject.netcracker.repos.AdvertRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("advertService")
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    AdvertRepo advertRepo;


    @Override
    public Advert findByOwnerId(Long id) {
        return advertRepo.findByOwnerId(id);
    }

    @Override
    public void saveAdvert(Advert advert) {
        advertRepo.save(advert);
    }

/*
    @Override
    public Set<Advert> findAllByAddDateIsNotNull() {
        return advertRepo.findAllByAddDateIsNotNull();
    }*/

    /*@Override
    public List<Advert> findAll() {
        return advertRepo.findAll();
    }*/
}
