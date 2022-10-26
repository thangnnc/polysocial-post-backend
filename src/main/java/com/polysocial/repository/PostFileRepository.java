package com.polysocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polysocial.entity.PostFile;

public interface PostFileRepository extends JpaRepository<PostFile, Long>{
    
}
