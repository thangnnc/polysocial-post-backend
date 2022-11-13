package com.polysocial.service;

import java.util.List;

import com.polysocial.dto.LikeDTO;
import com.polysocial.entity.Likes;

public interface LikeService {

    LikeDTO likeUnLike(LikeDTO dto) throws Exception;
}
