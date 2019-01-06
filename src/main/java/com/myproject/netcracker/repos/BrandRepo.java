package com.myproject.netcracker.repos;

import com.myproject.netcracker.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepo extends JpaRepository<Brand,Long> {

    Brand findByBrandId(Long Id);
    List<Brand> findAllByBrandIdIsNotNull();


}
