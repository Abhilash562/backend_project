package com.example.login.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.Entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

	Optional<Users> findByMobileNumber(String mobileNumber);

}
