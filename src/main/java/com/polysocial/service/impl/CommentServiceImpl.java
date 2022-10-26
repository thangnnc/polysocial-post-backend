package com.polysocial.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.polysocial.dto.CommentDTO;
import com.polysocial.dto.ListPostDTO;
import com.polysocial.dto.PostDTO;
import com.polysocial.entity.Comments;
import com.polysocial.entity.Posts;
import com.polysocial.exception.PolySocialErrorCode;
import com.polysocial.exception.PolySocialException;
import com.polysocial.repository.CommentRepository;
import com.polysocial.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentRepository commentRepository;

    
    
    @Override
    public CommentDTO save(CommentDTO dto) throws PolySocialException {
        try {
            Comments comment = modelMapper.map(dto, Comments.class);
            comment.setCreatedDate(new Date());
            commentRepository.save(comment);
            return dto;
        } catch (Exception e) {
            throw new PolySocialException(PolySocialErrorCode.GENERAL);
        }
    }
    
    @Override
    public CommentDTO findAllPage() {

//        List<Comments> listComment = commentRepository.findByPostId(2l);
        return  null;
    }

    
    @Override
    public CommentDTO update(CommentDTO dto) throws Exception {
        try {
            Optional<Comments> postByid = commentRepository.findById(dto.getCmtId());
            Comments comment = modelMapper.map(postByid, Comments.class);
            comment.setContent(dto.getContent());
            commentRepository.save(comment);
            return dto;
        } catch (Exception e) {
            throw new PolySocialException(PolySocialErrorCode.GENERAL);
        }
    }

    @Override
    public CommentDTO deleteById(Long id) throws Exception {
        Optional<Comments> comment = commentRepository.findById(id);
        if (!comment.isPresent()) {
            throw new PolySocialException(PolySocialErrorCode.ERROR_MSG_POST_ID_NOT_FOUND);
        }
        comment.get().setStatus(false);
        commentRepository.save(comment.get());
        return null;
    }

}