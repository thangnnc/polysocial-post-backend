package com.polysocial.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.consts.CommentAPI;
import com.polysocial.consts.PostAPI;
import com.polysocial.dto.CommentDTO;
import com.polysocial.dto.PostDTO;
import com.polysocial.dto.ResponseDTO;
import com.polysocial.service.CommentService;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping(CommentAPI.API_GET_COMMENT)
    public ResponseEntity<ResponseDTO<CommentDTO>> save(@RequestBody CommentDTO reuqest) {
        try {
            return ResponseEntity
                    .ok(ResponseDTO.responseSuccess("OK", commentService.save(reuqest)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseDTO.responseFail(e.getMessage()));
        }
    }

    @GetMapping(CommentAPI.API_GET_COMMENT)
    public ResponseEntity<ResponseDTO<CommentDTO>> findAll() {
        try {
            return ResponseEntity
                    .ok(ResponseDTO.responseSuccess("OK", commentService.findAllPage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseDTO.responseFail(e.getMessage()));
        }
    }

    @PutMapping(CommentAPI.API_PUT_COMMENT)
    public ResponseEntity<ResponseDTO<CommentDTO>> update(@RequestBody CommentDTO request,
            @PathVariable("id") long id) {
        request.setCmtId(id);
        try {
            return ResponseEntity
                    .ok(ResponseDTO.responseSuccess("OK", commentService.update(request)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseDTO.responseFail(e.getMessage()));
        }
    }

    @PutMapping(value = CommentAPI.API_DELETE_COMMENT)
    public ResponseEntity<ResponseDTO<CommentDTO>> delete(@PathVariable("id") long id) {
        try {
            return ResponseEntity
                    .ok(ResponseDTO.responseSuccess("OK", commentService.deleteById(id)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseDTO.responseFail(e.getMessage()));
        }
    }
}
