package com.polysocial.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.consts.LikeAPI;
import com.polysocial.dto.LikeDTO;
import com.polysocial.dto.ResponseDTO;
import com.polysocial.service.LikeService;

@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping(LikeAPI.API_GET_POST_LIKE)
    public ResponseEntity<ResponseDTO<LikeDTO>> like(@RequestBody LikeDTO request) {
        try {
            return ResponseEntity
                    .ok(ResponseDTO.responseSuccess("OK", likeService.likeUnLike(request)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseDTO.responseFail(e.getMessage()));
        }

    }

}
