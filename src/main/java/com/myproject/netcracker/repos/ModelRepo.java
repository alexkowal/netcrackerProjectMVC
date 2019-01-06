package com.myproject.netcracker.repos;

import com.myproject.netcracker.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepo extends JpaRepository<Model, Long> {


    Model findByBrandId(Long id);

    Model findByModelId(Long id);

    List<Model> findAllByModelId(Long id);

    List<Model> findAllByModelIdIsNotNull();

    List<Model> findAllByBrandId(Long brandId);

    Model findByName(String name);
}
