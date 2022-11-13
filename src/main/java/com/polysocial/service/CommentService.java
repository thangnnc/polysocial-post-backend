package com.polysocial.service;

import com.polysocial.dto.CommentDTO;
import com.polysocial.exception.PolySocialException;

public interface CommentService {

    CommentDTO deleteById(Long id) throws Exception;

    CommentDTO findAllPage();

    CommentDTO save(CommentDTO dto);
    
}
