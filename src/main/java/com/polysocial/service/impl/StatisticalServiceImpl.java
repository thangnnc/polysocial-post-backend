package com.polysocial.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polysocial.dto.GroupDTO;
import com.polysocial.dto.StatisticalDTO;
import com.polysocial.entity.Groups;
import com.polysocial.entity.Posts;
import com.polysocial.repository.CommentRepository;
import com.polysocial.repository.LikeRepository;
import com.polysocial.repository.StatisticalRepository;
import com.polysocial.service.StatisticalService;

@Service
public class StatisticalServiceImpl implements StatisticalService {

    @Autowired
    private StatisticalRepository statisticalRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<StatisticalDTO> getStatisticalByGroupId(Long groupId) {
        List<Posts> posts = statisticalRepository.getPostByGroup(groupId);
        List<StatisticalDTO> statisticalDTOs = new ArrayList();
        int countPost = statisticalRepository.countPostByGroup(groupId);
        for (Posts post : posts) {
            List<Groups> groups = statisticalRepository.getAllGroup();
            List<GroupDTO> groupDTO = groups.stream().map(element -> modelMapper.map(element, GroupDTO.class)).collect(Collectors.toList());
            StatisticalDTO statisticalDTO = new StatisticalDTO(post.getContent(),statisticalRepository.countLike(post.getPostId()), statisticalRepository.countComment(post.getPostId()), countPost, groupDTO);
            statisticalDTOs.add(statisticalDTO);
        }
        return statisticalDTOs;
    }

    
    
}
