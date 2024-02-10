package com.illiapinchuk.testtask.persistence.repository;

import com.illiapinchuk.testtask.persistence.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findUserByLogin(String login);

  boolean existsByLogin(String login);
}
