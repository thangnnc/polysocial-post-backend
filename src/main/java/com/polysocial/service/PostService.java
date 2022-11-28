package com.polysocial.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.polysocial.dto.ListPostDTO;
import com.polysocial.dto.PostDTO;
import com.polysocial.dto.PostFileDTO;

public interface PostService {

    ListPostDTO findAllPage(Integer page, Integer limit);

    PostDTO save(PostDTO dto);

	PostDTO findById(Long postId);
    
    void delete(Long postId);

    PostDTO update(PostDTO dto);

    PostDTO getOne(Long postId);
}
