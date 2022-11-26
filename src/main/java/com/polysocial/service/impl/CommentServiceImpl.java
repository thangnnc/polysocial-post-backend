package com.polysocial.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.polysocial.dto.CommentDTO;
import com.polysocial.dto.CommentReplyDTO;
import com.polysocial.entity.Comments;
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
    public CommentDTO save(CommentDTO dto)  {
        try {
            Comments comment = modelMapper.map(dto, Comments.class);
            comment.setStatus(true);
            commentRepository.save(comment);
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public CommentDTO findAllPage() {
        return  null;
    }


    @Override
    public CommentDTO deleteById(Long id) throws Exception {
        Optional<Comments> comment = commentRepository.findById(id);
        if (!comment.isPresent()) {
            throw new PolySocialException(PolySocialErrorCode.ERROR_MSG_POST_ID_NOT_FOUND);
        }
        comment.get().setStatus(false);
        commentRepository.delete(comment.get());
        return null;
    }

    @Override
    public List<CommentDTO> getCommentByPostId(Long postId) {
        List<Comments> listComment = commentRepository.findByPostId(postId);
        List<CommentDTO> listCommentDTO = new ArrayList<>();
        for (Comments comments : listComment) {
            CommentDTO commentDTO = modelMapper.map(comments, CommentDTO.class);
            List<Comments> list = commentRepository.findByCmtId(comments.getCmtId());
            commentDTO.setCommentReplies(list.stream().map(comment -> modelMapper.map(comment, CommentReplyDTO.class)).collect(Collectors.toList()));
            listCommentDTO.add(commentDTO);
        }
        return listCommentDTO;
    }

    @Override
    public CommentDTO replyComment(CommentDTO dto) {
        try {
            Comments comment = modelMapper.map(dto, Comments.class);
            Comments cmt = commentRepository.findById(dto.getIdReply()).get();
            comment.setPostId(cmt.getPostId());
            comment.setUserId(dto.getUserId());
            commentRepository.save(comment);
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CommentDTO update(CommentDTO dto) throws Exception {
        return save(dto);
    }

}
