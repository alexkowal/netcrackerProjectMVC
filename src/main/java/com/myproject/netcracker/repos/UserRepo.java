package com.myproject.netcracker.repos;

import com.myproject.netcracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByLogin(String login);

}
