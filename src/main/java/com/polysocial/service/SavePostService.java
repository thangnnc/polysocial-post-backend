package com.polysocial.service;

import java.util.List;

import com.polysocial.dto.SavePostDTO;
import com.polysocial.dto.SavePostDetailDTO;

public interface SavePostService {
    
    SavePostDTO savePost(SavePostDTO savePostDTO);

    List<SavePostDetailDTO> getAllSavePost(Long userId);

    void deleteSavePost(Long savePostId);

    
}
