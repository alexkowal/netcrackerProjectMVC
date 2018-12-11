package com.myproject.netcracker.repos;

import com.myproject.netcracker.domain.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertRepo extends JpaRepository<Advert, Long> {
    Advert findByOwnerId(Long id);
    List<Advert> findAllByAddDateIsNotNull();

}
