package com.polysocial.rest.controller;

import com.polysocial.consts.PostAPI;

import com.polysocial.dto.PostDTO;
import com.polysocial.dto.PostFileDTO;
import com.polysocial.dto.ResponseDTO;
import com.polysocial.dto.ListPostDTO;
import com.polysocial.service.PostService;
import com.polysocial.utils.ValidateUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(PostAPI.API_GET_POST)
    public ResponseEntity getPost(@RequestParam("page") Optional<Integer> page,
            @RequestParam("limit") Optional<Integer> limit) {
        ListPostDTO response = postService.findAllPage(page.orElse(0), limit.orElse(10));
        return ResponseEntity.ok(response);
    }

    @PostMapping(PostAPI.API_GET_POST)
    public ResponseEntity create(@RequestBody PostDTO request) {
        if (ValidateUtils.isNullOrEmpty(request.getContent())) {
            ResponseDTO response = new ResponseDTO();
            response.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else {
            PostDTO response = postService.save(request);
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping(value= PostAPI.API_UPDATE_POST, consumes = "application/json")
    public ResponseEntity update(@RequestBody PostDTO request) {
        if (ValidateUtils.isNullOrEmpty(request.getContent())) {
            ResponseDTO response = new ResponseDTO();
            response.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else {
            PostDTO response = postService.update(request);
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping(PostAPI.API_DELETE_POST)
    public ResponseEntity delete(@RequestParam Long postId) {
        try {
            postService.delete(postId);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(PostAPI.API_GET_ONE_POST)
    public ResponseEntity getOne(@RequestParam("postId") Long postId) {
        try {
            PostDTO response = postService.getOne(postId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);

        }
    }
    
    @GetMapping(PostAPI.API_GET_POST_BY_GROUP)
    public ResponseEntity getPostByGroup(@RequestParam("groupId") Long groupId, @RequestParam("page") Optional<Integer> page,
            @RequestParam("limit") Optional<Integer> limit) {
        ListPostDTO response = postService.findAllPageByGroup(groupId, page.orElse(0), limit.orElse(10));
        return ResponseEntity.ok(response);
    }

}
