package com.polysocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.polysocial.entity.Groups;
import com.polysocial.entity.Likes;
import com.polysocial.entity.Posts;


@Repository
public interface StatisticalRepository extends JpaRepository<Likes, Long>{

    @Query("Select count(p) from Likes p where p.postId =?1")
    int countLike(Long postId);

    @Query("Select count(p) from Comments p where p.postId =?1")
    int countComment(Long postId);

    @Query("Select count(p) from Posts p where p.groupId =?1")
    int countPostByGroup(Long groupId);

    @Query("SELECT p FROM Posts p WHERE p.groupId= ?1")
    List<Posts> getPostByGroup(Long groupId);

    @Query("SELECT p FROM Groups p")
    List<Groups> getAllGroup();
}
