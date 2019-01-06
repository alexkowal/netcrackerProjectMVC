package com.myproject.netcracker.repos;

import com.myproject.netcracker.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepo extends JpaRepository<Picture,Long> {

}
