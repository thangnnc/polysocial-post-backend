package com.polysocial.service.impl;

import com.polysocial.dto.CommentDTO;
import com.polysocial.dto.ListPostDTO;
import com.polysocial.dto.PostDTO;
import com.polysocial.entity.Comments;
import com.polysocial.entity.Likes;
import com.polysocial.entity.Posts;
import com.polysocial.exception.PolySocialErrorCode;
import com.polysocial.exception.PolySocialException;
import com.polysocial.repository.CommentRepository;
import com.polysocial.repository.LikeRepository;
import com.polysocial.repository.PostRepository;
import com.polysocial.service.PostFileService;
import com.polysocial.service.PostService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostFileService postFileService;
    
    @Autowired CommentRepository commentRepository;
    
    @Autowired
    private LikeRepository likeRepository;

  @Override
  public ListPostDTO findAllPage(Integer page, Integer limit) {
      ListPostDTO listPostDTO = new ListPostDTO();
      Pageable pageable = PageRequest.of(page, limit);
      Page<Posts> pagePost = this.postRepository.findAll(pageable);
      List<Posts> listPost = pagePost.getContent();

      if (listPost.size() > 0) {
          List<PostDTO> listPostConver = new ArrayList<>();
          for (Posts post : listPost) {
              List<Comments> cm = commentRepository.findByPostId(post.getPostId());
              PostDTO dto = modelMapper.map(post, PostDTO.class);
              List<CommentDTO> listCommentConver = new ArrayList<>();
              for (Comments comments : cm) {
                  CommentDTO cmt = modelMapper.map(comments, CommentDTO.class);
                  listCommentConver.add(cmt);
              }
              
              Long countLike = likeRepository.countLike(post.getPostId());
              Long countComment = commentRepository.countComment(post.getPostId());
              
              dto.setListComment(listCommentConver);
              dto.setCountLike(countLike);
              dto.setCountComment(countComment);
              
              listPostConver.add(dto);
              
              
          }
          listPostDTO.setListPostDTO(listPostConver);
//          System.out.println("like->"+listPostConver);

      }
      listPostDTO.setTotalPage((int) Math.ceil((double) (pagePost.getTotalElements()) / limit));
      listPostDTO.setTotalItem((int) pagePost.getTotalElements());
      listPostDTO.setPage(page);
      return listPostDTO;
  }

//    @Override
//    public ListPostDTO findAllPage(Integer page, Integer limit) {
//        ListPostDTO listPostDTO = new ListPostDTO();
//        Pageable pageable = PageRequest.of(page, limit);
//        Page<Posts> pagePost = this.postRepository.findAll(pageable);
//        List<Posts> listPost = pagePost.getContent();
//
//        if (listPost.size() > 0) {
//            List<PostDTO> listPostConver = new ArrayList<>();
//            for (Posts post : listPost) {
//                List<Comments> cm = commentRepository.findByPostId(post.getPostId());
//                PostDTO dto = modelMapper.map(post, PostDTO.class);
//                List<CommentDTO> listCommentConver = new ArrayList<>();
//                for (Comments comments : cm) {
//                    CommentDTO cmt = modelMapper.map(comments, CommentDTO.class);
//                    listCommentConver.add(cmt);
//                }
//                dto.setListComment(listCommentConver);
//                listPostConver.add(dto);
//                
//            }
//            listPostDTO.setListPostDTO(listPostConver);
//
//        }
//        listPostDTO.setTotalPage((int) Math.ceil((double) (pagePost.getTotalElements()) / limit));
//        listPostDTO.setTotalItem((int) pagePost.getTotalElements());
//        listPostDTO.setPage(page);
//        return listPostDTO;
//    }

    public PostDTO save(PostDTO dto) throws Exception {
        try {
            Posts post = modelMapper.map(dto, Posts.class);
            post.setCreatedDate(new Date());
            post.setStatus(true);
            postRepository.save(post);
            return dto;
        } catch (Exception e) {
            throw new PolySocialException(PolySocialErrorCode.GENERAL);
        }

    }

    @Override
    public PostDTO update(PostDTO dto) throws Exception {
        try {
            Optional<Posts> postByid = postRepository.findById(dto.getPostId());
            Posts post = modelMapper.map(postByid, Posts.class);
            post.setContent(dto.getContent());
            postRepository.save(post);
            return dto;
        } catch (Exception e) {
            throw new PolySocialException(PolySocialErrorCode.GENERAL);
        }

    }

}
