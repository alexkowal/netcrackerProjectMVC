package com.myproject.netcracker.service;

import com.myproject.netcracker.domain.Brand;
import com.myproject.netcracker.repos.AdvertRepo;
import com.myproject.netcracker.repos.BrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("brandService")
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepo brandRepo;

    @Override
    public Brand findByBrandId(Long id) {
        return brandRepo.findByBrandId(id);
    }

    @Override
    public List<Brand> findAllByBrandIdIsNotNull() {
        return brandRepo.findAllByBrandIdIsNotNull();
    }
}
