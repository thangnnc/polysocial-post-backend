package com.polysocial.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.polysocial.entity.Comments;
import com.polysocial.entity.Posts;
import com.polysocial.entity.Users;

import org.hibernate.Hibernate;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO implements Serializable {
    
//    private Long cmtId;

    private String content;

    private Date createdDate;

    private Long postId;

    private Long userId;
    
    private Users user;

    
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Comments comments = (Comments) o;
//        return cmtId != null && Objects.equals(cmtId, comments.cmtId);
//    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
