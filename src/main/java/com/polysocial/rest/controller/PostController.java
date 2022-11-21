package com.polysocial.rest.controller;

import com.polysocial.consts.PostAPI;

import com.polysocial.dto.PostDTO;
import com.polysocial.dto.ResponseDTO;
import com.polysocial.exception.PolySocialException;
import com.polysocial.dto.ListPostDTO;
import com.polysocial.service.PostService;
import com.polysocial.utils.ValidateUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    
    @PostMapping(PostAPI.API_UPLOADFILE_POST)
    public List<String> add(@RequestParam(value = "file", required = false) List<MultipartFile> fi) throws IOException {
        return postService.saveFile(fi);
    }
    

}
