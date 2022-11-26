package com.polysocial.dto;

import java.io.Serializable;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.polysocial.entity.Groups;
import com.polysocial.entity.Posts;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class StatisticalDTO implements Serializable{
    private String content;
    private Integer likeCount;
    private Integer commentCount;
    private Integer postCount;
    private List<GroupDTO> groups;
    private List<Posts> posts;


    public StatisticalDTO(String content, Integer likeCount, Integer commentCount, Integer postCount, List<GroupDTO> groups) {
        this.content = content;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.postCount = postCount;
        this.groups = groups;
    }

}
