package dev.sgwrth.orderlogger.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sgwrth.orderlogger.entity.CustomUser;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {

	Optional<CustomUser> findByUsername(String username);

}
