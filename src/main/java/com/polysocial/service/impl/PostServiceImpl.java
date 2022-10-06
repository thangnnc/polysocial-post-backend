package com.polysocial.service.impl;

import com.polysocial.dto.PostDTO;
import com.polysocial.entity.Post;
import com.polysocial.service.PostService;
import com.polysocial.utils.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDTO getPost() {
        Logger.info("Start getPost service");
        try {
            Post exercise = new Post("Cao Thăng", "Mậu Phi", "Hoàng Duy", "Đăng Trường");
            PostDTO response = modelMapper.map(exercise, PostDTO.class);
            return response;
        }catch (Exception ex){
            Logger.error("Get post exception: " + ex);
            return null;
        }
    }
}
