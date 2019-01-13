package com.myproject.netcracker.repos;

import com.myproject.netcracker.domain.Charact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharactRepo extends JpaRepository<Charact, Long> {


    Charact findCharactByCharactId(Long id);

    Charact findCharactByBrandId(Long id);

    Charact findCharactByModelId(Long id);

    // Charact findCharactByBody();

    Charact findCharactByTransmission(String transmission);

    List<Charact> findAllByBrandIdIsNotNull();

    List<Charact> findAllByBrandId(Long id);

    List<Charact> findAllByBrandIdAndAndModelId(Long brandId, Long modelId);

    List<Charact> findAllByBody(String body);
    List<Charact> findAllByTransmission(String body);
    List<Charact> findAllByDriveUnit(String body);
    List<Charact> findAllByPower(Integer power);



    //List<Charact> findAllByBody();
    //List<Charact> findAllByTransmission();
    //List<Charact> findAllByDriveUnit();
    //List<Charact> findAllByPower();
}
