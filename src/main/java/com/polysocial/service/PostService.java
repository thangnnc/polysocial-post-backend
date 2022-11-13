package com.polysocial.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.polysocial.dto.ListPostDTO;
import com.polysocial.dto.PostDTO;

public interface PostService {

    ListPostDTO findAllPage(Integer page, Integer limit);

    PostDTO save(PostDTO dto);

    List<String> saveFile(List<MultipartFile> fi) throws IOException;

}
