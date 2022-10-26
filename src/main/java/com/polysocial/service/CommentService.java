package com.polysocial.service;

import com.polysocial.dto.CommentDTO;
import com.polysocial.exception.PolySocialException;

public interface CommentService {

    CommentDTO save(CommentDTO dto) throws PolySocialException;

    CommentDTO update(CommentDTO dto) throws Exception;

    CommentDTO deleteById(Long id) throws Exception;

    CommentDTO findAllPage();
    
}
