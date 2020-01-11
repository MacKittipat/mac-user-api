package com.mackittipat.userapi.repository;

import com.mackittipat.userapi.dto.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "SELECT " +
            "u.* " +
            "FROM user as u, role as r, user_role as ur " +
            "WHERE " +
            "u.id=ur.user_id AND " +
            "r.id=ur.role_id AND " +
            "r.name=?1", nativeQuery = true)
    List<User> findByRoleName(String roleName);
}
