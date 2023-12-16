package com.jennifer.sbank.repositories;

import com.jennifer.sbank.enums.Role;
import com.jennifer.sbank.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Page<User> findByRole(Role role, Pageable pageable);

}
