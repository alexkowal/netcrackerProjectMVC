package com.myproject.netcracker.repos;

import com.myproject.netcracker.domain.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkRepo extends JpaRepository<Mark, Long> {

    List<Mark> findAllByUserId(Long id);

    Mark findByAdvIdAndUserId(Long aId, Long uId);

}
