package com.polysocial.repository;


import org.springframework.stereotype.Repository;

import com.polysocial.entity.SavePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface SavePostRepo extends JpaRepository<SavePost, Long> {
    
    @Query(value = "SELECT o FROM SavePost o WHERE o.postId = ?1 AND o.userId = ?2")
    SavePost findSavePostByPostIdAndUserId(Long postId, Long userId);
}
