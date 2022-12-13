package com.polysocial.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.polysocial.consts.*;
import com.polysocial.dto.SavePostDTO;
import com.polysocial.service.SavePostService;

@RestController
public class SavePostController {
    
    @Autowired
    private SavePostService savePostService;

    @PostMapping(value = SavePostAPI.API_SAVE_POST, consumes = "application/json")
    public ResponseEntity savePost(@RequestBody SavePostDTO savePostDTO) {
        try{
            return ResponseEntity.ok(savePostService.savePost(savePostDTO));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(SavePostAPI.API_GET_ALL_SAVE_POST)
    public ResponseEntity getAllSavePost(@RequestParam Long userId) {
        try{
            return ResponseEntity.ok(savePostService.getAllSavePost(userId));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(SavePostAPI.API_DELETE_SAVE_POST)
    public ResponseEntity deleteSavePost(@RequestParam Long savePostId) {
        try{
            savePostService.deleteSavePost(savePostId);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
