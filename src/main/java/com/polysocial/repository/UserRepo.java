package com.polysocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polysocial.entity.Users;

@Repository
public interface UserRepo  extends JpaRepository<Users, Long>{
    
}
