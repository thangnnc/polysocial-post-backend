package com.polysocial.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.polysocial.entity.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponseDTO {
    
    private String content;

    private LocalDateTime createdDate = LocalDateTime.now();

    private Long userId;
    
    private Users user;

    private List<CommentReplyDTO> commentReplies;

    private Long idReply;

    public CommentResponseDTO(String content, Long userId, List<CommentReplyDTO> commentReplies) {
        this.content = content;
        this.userId = userId;
        this.commentReplies = commentReplies;
    }

}
