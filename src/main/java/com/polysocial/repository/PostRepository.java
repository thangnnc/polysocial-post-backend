package com.polysocial.repository;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.polysocial.entity.Posts;

public interface PostRepository extends JpaRepository<Posts, Long>{
    
	@Query("Select p from Posts p where p.status = 1 order by p.createdDate DESC")
	Page<Posts> findAllDESC(Pageable pageable);

	@Modifying
	@Transactional
	@Query("update Posts p set p.status = 0 where p.postId = ?1")
	void deletePost(Long postId);

	@Query("Select p from Posts p where p.status = 1 and p.groupId =?1 order by p.createdDate DESC")
	Page<Posts> findAllDESCGroup(Long groupId, Pageable pageable);
}
