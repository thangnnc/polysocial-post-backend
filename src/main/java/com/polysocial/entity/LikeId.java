package com.polysocial.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class LikeId implements Serializable{
    
    @Column(name = "userId")
    private Long userId;

    @Column(name="postId")
    private Long postId;
}
