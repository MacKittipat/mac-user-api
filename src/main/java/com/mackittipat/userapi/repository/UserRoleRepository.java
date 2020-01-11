package com.mackittipat.userapi.repository;

import com.mackittipat.userapi.dto.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    UserRole findByUserId(Long userId);

}
