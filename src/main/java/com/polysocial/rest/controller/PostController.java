package com.polysocial.rest.controller;

import com.polysocial.consts.PostAPI;
import com.polysocial.dto.PostDTO;
import com.polysocial.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(PostAPI.API_GET_POST)
    public PostDTO getPost(){
        PostDTO response = postService.getPost();
        return response;
    }
}
