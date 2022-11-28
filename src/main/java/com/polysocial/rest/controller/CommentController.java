package com.polysocial.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.consts.CommentAPI;
import com.polysocial.dto.CommentDTO;
import com.polysocial.service.CommentService;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping(CommentAPI.API_GET_COMMENT)
    public ResponseEntity saveComment(@RequestBody CommentDTO request) {
        CommentDTO response = commentService.save(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping(CommentAPI.API_GET_COMMENT_BY_POST)
    public ResponseEntity getCommentByPostId(@RequestParam Long postId, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return ResponseEntity.ok(commentService.getCommentByPostId(postId, page, size));
    }

    @PostMapping(CommentAPI.API_REPLY_COMMENT)
    public ResponseEntity replyComment(@RequestBody CommentDTO request) {
        CommentDTO response = commentService.replyComment(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(CommentAPI.API_DELETE_COMMENT)
    public ResponseEntity deleteComment(@RequestParam Long commentId) throws Exception {
        try {
            commentService.deleteById(commentId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping(CommentAPI.API_PUT_COMMENT)
    public ResponseEntity updateComment(@RequestBody CommentDTO reuqest) throws Exception {
        try {
            CommentDTO response = commentService.update(reuqest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);

        }
    }
}
