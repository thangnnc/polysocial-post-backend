package com.polysocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polysocial.entity.LikeId;
import com.polysocial.entity.Likes;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeRepository extends JpaRepository<Likes, LikeId>{
    
    @Query("SELECT COUNT(*) FROM Likes l WHERE l.postId=:postId AND status=1")
    Long countLike(@Param("postId") Long postId);
    
    @Query(value = "SELECT u.studentCode, l.postId, l.status,u.userId FROM Likes l\n"
    		+ "  JOIN Users u on u.userId = l.userId\n"
    		+ "  WHERE postId = ?1 AND status=1", nativeQuery = true)
    List<Object[]>  findByPostId(Long postId);

    @Query("SELECT o FROM Likes o WHERE o.postId=:postId")
    List<Likes> findPostByPostId(@Param("postId") Long postId);
}
