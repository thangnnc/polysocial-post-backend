package com.polysocial.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.consts.CommentAPI;
import com.polysocial.dto.CommentDTO;
import com.polysocial.service.CommentService;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping(CommentAPI.API_GET_COMMENT)
    public ResponseEntity saveComment(@RequestBody CommentDTO reuqest) {
        CommentDTO response =commentService.save(reuqest);
        return ResponseEntity.ok(response);
    }
//
//    @GetMapping(CommentAPI.API_GET_COMMENT)
//    public ResponseEntity<ResponseDTO<CommentDTO>> findAll() {
//        try {
//            return ResponseEntity
//                    .ok(ResponseDTO.responseSuccess("OK", commentService.findAllPage()));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body(ResponseDTO.responseFail(e.getMessage()));
//        }
//    }
//
//    @PutMapping(CommentAPI.API_PUT_COMMENT)
//    public ResponseEntity<ResponseDTO<CommentDTO>> update(@RequestBody CommentDTO request,
//            @PathVariable("id") long id) {
//        request.setCmtId(id);
//        try {
//            return ResponseEntity
//                    .ok(ResponseDTO.responseSuccess("OK", commentService.update(request)));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body(ResponseDTO.responseFail(e.getMessage()));
//        }
//    }
//
//    @PutMapping(value = CommentAPI.API_DELETE_COMMENT)
//    public ResponseEntity<ResponseDTO<CommentDTO>> delete(@PathVariable("id") long id) {
//        try {
//            return ResponseEntity
//                    .ok(ResponseDTO.responseSuccess("OK", commentService.deleteById(id)));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body(ResponseDTO.responseFail(e.getMessage()));
//        }
//    }
}
