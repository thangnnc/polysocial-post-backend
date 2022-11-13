package com.polysocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.polysocial.entity.PostFile;

public interface PostFileRepository extends JpaRepository<PostFile, Long>{
    
    @Query("SELECT p FROM PostFile p WHERE p.postId = :postId")
    List<PostFile> findByPostId(@Param("postId") Long postId);
}
