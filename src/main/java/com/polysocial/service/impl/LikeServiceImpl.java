package com.polysocial.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polysocial.dto.LikeDTO;
import com.polysocial.entity.LikeId;
import com.polysocial.entity.Likes;
import com.polysocial.exception.PolySocialErrorCode;
import com.polysocial.exception.PolySocialException;
import com.polysocial.repository.LikeRepository;
import com.polysocial.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public LikeDTO likeUnLike(LikeDTO dto) throws Exception {
        try {
            LikeId likeId = modelMapper.map(dto, LikeId.class);
            Optional<Likes> likeByUserPost = likeRepository.findById(likeId);
            Likes likeExist = modelMapper.map(likeByUserPost, Likes.class);
            Likes like = modelMapper.map(likeId, Likes.class);
            if (likeExist == null) {
                like.setStatus(true);
                likeRepository.save(like);
            } else {
                likeExist.setStatus(!likeExist.getStatus());
                likeRepository.save(likeExist);
            }
            return dto;
        } catch (Exception e) {
            throw new PolySocialException(PolySocialErrorCode.ERROR_MSG_POST_ID_NOT_FOUND);
        }

    }
    
//    public LikeDTO countLike(LikeDTO dto) {
//        Likes count = likeRepository.countLike(dto.getPostId());
//    }

}
