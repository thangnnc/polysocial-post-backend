package com.polysocial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.polysocial.dto.CommentDTO;

public interface CommentService {

    CommentDTO deleteById(Long id) throws Exception;

    CommentDTO findAllPage();

    CommentDTO save(CommentDTO dto);

    CommentDTO update(CommentDTO dto) throws Exception;
    
    List<CommentDTO> getCommentByPostId(Long postId, Optional<Integer> page, Optional<Integer> limit);

    CommentDTO replyComment(CommentDTO dto);
}
