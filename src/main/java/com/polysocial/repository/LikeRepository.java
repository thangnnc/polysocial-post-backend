package com.polysocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polysocial.entity.LikeId;
import com.polysocial.entity.Likes;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeRepository extends JpaRepository<Likes, LikeId>{
    
    @Query("SELECT COUNT(*) FROM Likes l WHERE l.postId=:postId AND status=1")
    Long countLike(@Param("postId") Long postId);
}
