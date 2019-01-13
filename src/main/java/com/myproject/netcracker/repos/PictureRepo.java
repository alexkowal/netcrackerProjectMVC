package com.myproject.netcracker.repos;

import com.myproject.netcracker.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepo extends JpaRepository<Picture,Long> {

    List<Picture> findByAdvertId(Long id);


}
