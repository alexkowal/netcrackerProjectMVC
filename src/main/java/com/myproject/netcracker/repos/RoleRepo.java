package com.myproject.netcracker.repos;

import com.myproject.netcracker.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RoleRepo {

    public interface UserRepo extends JpaRepository<Role,Long> {
        Role findByDescr(String descr);

    }

}
