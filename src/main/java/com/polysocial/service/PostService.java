package com.polysocial.service;

import java.util.List;


import com.polysocial.dto.ListPostDTO;
import com.polysocial.dto.PostDTO;

public interface PostService {

    ListPostDTO findAllPage(Integer page, Integer limit);

    PostDTO update(PostDTO dto) throws Exception;

    PostDTO save(PostDTO dto) throws Exception;

}
