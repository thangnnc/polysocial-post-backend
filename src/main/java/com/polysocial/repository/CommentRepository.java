package com.polysocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.polysocial.entity.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long>{

    @Query("SELECT p FROM Comments p WHERE p.postId = :postId and idReply = null")
    List<Comments> findByPostId(@Param("postId") Long postId);
    
    @Query("SELECT COUNT(*) FROM Comments l WHERE l.postId=:postId")
    Long countComment(@Param("postId") Long postId);

    @Query("SELECT p FROM Comments p WHERE p.idReply = :idReply")
    List<Comments> findByCmtId(@Param("idReply") Long idReply);

}
