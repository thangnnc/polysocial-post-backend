package com.polysocial.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;

@Data
@Entity
@RequiredArgsConstructor
@IdClass(LikeId.class)
public class Likes implements Serializable {
    
    @Id
    @Column(name = "userId")
    private Long userId;

    @Id
    @Column(name="postId")
    private Long postId;
 
    @ManyToOne(targetEntity = Posts.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId", updatable = false, insertable = false)
    private Posts post;
    
    private Boolean status;
    
}
