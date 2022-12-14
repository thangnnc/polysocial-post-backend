package com.polysocial.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polysocial.dto.SavePostDTO;
import com.polysocial.dto.SavePostDetailDTO;
import com.polysocial.entity.PostFile;
import com.polysocial.entity.Posts;
import com.polysocial.entity.SavePost;
import com.polysocial.repository.PostFileRepository;
import com.polysocial.repository.PostRepository;
import com.polysocial.repository.SavePostRepo;
import com.polysocial.repository.UserRepo;
import com.polysocial.service.SavePostService;

@Service
public class SavePostServiceImpl implements SavePostService{


    @Autowired
    private SavePostRepo savePostRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private PostFileRepository postFileRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public SavePostDTO savePost(SavePostDTO savePostDTO) {
        SavePost sv = savePostRepo.findSavePostByPostIdAndUserId(savePostDTO.getPostId(), savePostDTO.getUserId());
        if(sv != null ) return savePostDTO;
        SavePost savePost = modelMapper.map(savePostDTO, SavePost.class);
        savePost = savePostRepo.save(savePost);
        savePostDTO = modelMapper.map(savePost, SavePostDTO.class);
        return savePostDTO;
    }

    @Override
    public List<SavePostDetailDTO> getAllSavePost(Long userId) {
        List<SavePost> savePosts = savePostRepo.findAll(); 
        List<SavePostDetailDTO> list = new ArrayList();
        for (SavePost savePost : savePosts) {
            SavePostDetailDTO savePostDetailDTO = modelMapper.map(savePost, SavePostDetailDTO.class);
            Posts post = postRepo.findById(savePost.getPostId()).get();
            savePostDetailDTO.setContent(post.getContent());
            savePostDetailDTO.setFullName(userRepo.findById(post.getUser().getUserId()).get().getFullName());
            savePostDetailDTO.setCreatedDatePost(post.getCreatedDate());
            try{
                PostFile postFile = postFileRepo.findByPostId(savePost.getPostId()).get(0);
                savePostDetailDTO.setUrl(postFile.getUrl());

            }catch(Exception e){
            }
            list.add(savePostDetailDTO);
        }
        return list;
    }

    @Override
    public void deleteSavePost(Long savePostId) {
        savePostRepo.deleteById(savePostId);
    }
    
}
