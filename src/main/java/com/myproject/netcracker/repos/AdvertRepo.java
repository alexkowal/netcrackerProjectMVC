package com.myproject.netcracker.repos;

import com.myproject.netcracker.domain.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AdvertRepo extends JpaRepository<Advert, Long> {


    Advert findByOwnerId(Long id);

    Set<Advert> findAllByAddDateIsNotNull();

    List<Advert> findAllByOwnerId(Long id);

    List<Advert> findAllByBrandId(Long id);

    List<Advert> findAllByModelId(Long id);

    List<Advert> findAllByMileageBetween(Integer start, Integer fin);

    List<Advert> findAllByCharactId(Long id);

    List<Advert> findAllByFactYear(Long year);

    List<Advert> findAdvertsByCharactId(Integer id);


}
