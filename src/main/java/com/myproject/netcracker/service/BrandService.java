package com.myproject.netcracker.service;

import com.myproject.netcracker.domain.Brand;

import java.util.List;

public interface BrandService {

    Brand findByBrandId(Long id);
    List<Brand> findAllByBrandIdIsNotNull();

}
