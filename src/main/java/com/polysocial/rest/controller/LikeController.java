package com.polysocial.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.consts.LikeAPI;
import com.polysocial.dto.LikeDTO;
import com.polysocial.dto.ResponseDTO;
import com.polysocial.service.LikeService;
import com.polysocial.utils.ValidateUtils;

@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;

  @PostMapping(LikeAPI.API_GET_POST_LIKE)
  public ResponseEntity like (@RequestBody LikeDTO request) throws Exception {
      if (ValidateUtils.isNullOrEmpty(request.getPostId())) {
          ResponseDTO response = new ResponseDTO();
          response.setStatus(HttpStatus.BAD_REQUEST);
          return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
      } else {
          LikeDTO response = likeService.likeUnLike(request);
          return ResponseEntity.ok(response);
      }

  }

}
