package com.polysocial.rest.controller;

import com.polysocial.consts.PostAPI;

import com.polysocial.dto.PostDTO;
import com.polysocial.dto.PostFileUploadDTO;
import com.polysocial.dto.ResponseDTO;
import com.polysocial.exception.PolySocialException;
import com.polysocial.dto.ListPostDTO;
import com.polysocial.service.PostFileService;
import com.polysocial.service.PostService;
import com.polysocial.utils.ValidateUtils;

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
    
    @Autowired PostFileService postFileService;
    @GetMapping(PostAPI.API_GET_POST)
    public ResponseEntity getPost(@RequestParam("page") Optional<Integer> page,
            @RequestParam("limit") Optional<Integer> limit) {
        ListPostDTO response = postService.findAllPage(page.orElse(0), limit.orElse(2));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(PostAPI.API_GET_POST)
    public ResponseEntity<ResponseDTO<PostDTO>> create(@RequestBody PostDTO request) {
        try {
            return ResponseEntity
                    .ok(ResponseDTO.responseSuccess("OK", postService.save(request)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseDTO.responseFail(e.getMessage()));
        }

    }

    @PutMapping(PostAPI.API_PUT_DELETE)
    public ResponseEntity<ResponseDTO<PostDTO>> update(@RequestBody PostDTO request, @PathVariable("id") long id) {
        request.setPostId(id);
        try {
            return ResponseEntity
                    .ok(ResponseDTO.responseSuccess("OK", postService.update(request)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseDTO.responseFail(e.getMessage()));
        }
    }

//    @DeleteMapping(value = PostAPI.API_PUT_DELETE)
//    public ResponseEntity<ResponseDTO<PostDTO>> delete(@PathVariable("id") long id) {
//        try {
//            return ResponseEntity
//                    .ok(ResponseDTO.responseSuccess("OK", postService.deleteById(id)));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body(ResponseDTO.responseFail(e.getMessage()));
//        }
//    }
    @PostMapping("/upfile")
    public PostFileUploadDTO saveFile(@RequestParam(value = "file", required = false) MultipartFile fi) throws PolySocialException {
        return postFileService.saveFile(fi);
    }

}
