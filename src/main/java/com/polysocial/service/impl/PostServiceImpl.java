package com.polysocial.service.impl;


import com.polysocial.dto.CommentResponseDTO;
import com.polysocial.dto.ListPostDTO;
import com.polysocial.dto.PostDTO;
import com.polysocial.dto.PostFileDTO;
import com.polysocial.dto.PostFileResponseDTO;
import com.polysocial.dto.PostResponseDTO;
import com.polysocial.entity.Comments;
import com.polysocial.entity.PostFile;
import com.polysocial.entity.Posts;
import com.polysocial.repository.CommentRepository;
import com.polysocial.repository.LikeRepository;
import com.polysocial.repository.PostFileRepository;
import com.polysocial.repository.PostRepository;
import com.polysocial.service.PostService;


import java.time.LocalDateTime;
import java.util.ArrayList;
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
    CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    PostFileRepository postFileRepository;

    @Override
    public ListPostDTO findAllPage(Integer page, Integer limit) {
        ListPostDTO listPostDTO = new ListPostDTO();
        Pageable pageable = PageRequest.of(page, limit);
        Page<Posts> pagePost = this.postRepository.findAllDESC(pageable);
        List<Posts> listPost = pagePost.getContent();

        if (listPost.size() > 0) {
            List<PostResponseDTO> listPostConver = new ArrayList<>();
            for (Posts post : listPost) {
                List<Comments> cm = commentRepository.findByPostId(post.getPostId());
                List<PostFile> pf = postFileRepository.findByPostId(post.getPostId());
             
                PostResponseDTO dto = modelMapper.map(post, PostResponseDTO.class);
                
                List<CommentResponseDTO> listCommentConver = new ArrayList<>();
                
                for (Comments comments : cm) {
                    CommentResponseDTO cmt = modelMapper.map(comments, CommentResponseDTO.class);
                    listCommentConver.add(cmt);
                }
                List<PostFileResponseDTO> listUrl = new ArrayList<>();
                for (PostFile postFile : pf) {
                    PostFileResponseDTO pfres = modelMapper.map(postFile, PostFileResponseDTO.class);
                    listUrl.add(pfres);
                }
                dto.setListUrl(listUrl);
                Long countLike = likeRepository.countLike(post.getPostId());
                Long countComment = commentRepository.countComment(post.getPostId());
                dto.setListComment(listCommentConver);
                dto.setCountLike(countLike);
                dto.setCountComment(countComment);
                dto.setStatus(post.getStatus());
                listPostConver.add(dto);

            }
            listPostDTO.setListPostDTO(listPostConver);

        }
        listPostDTO.setTotalPage((int) Math.ceil((double) (pagePost.getTotalElements()) / limit));
        listPostDTO.setTotalItem((int) pagePost.getTotalElements());
        listPostDTO.setPage(page);
        return listPostDTO;
    }


    @Override
    public PostDTO save(PostDTO dto) {
        try {
            Posts post = modelMapper.map(dto, Posts.class);
            post.setCreatedDate(LocalDateTime.now());
            post.setStatus(true);
            Posts entity = postRepository.save(post);
            for(int i = 0; i< dto.getListPath().size(); i++) {
                String type = dto.getListPath().get(i).substring(dto.getListPath().get(i).lastIndexOf(".") + 1);
                PostFile file = new PostFile(dto.getListPath().get(i), type, entity.getPostId());
                postFileRepository.save(file);
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
	public PostDTO findById(Long postId) {
    	try {
    		Optional<Posts> postById = this.postRepository.findById(postId);
        	if(postById.isPresent()) {
        		throw new Exception();
        	}
        	PostDTO dto = modelMapper.map(postById, PostDTO.class);
        	return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }

    @Override
    public void delete(Long postId) {
        postRepository.deletePost(postId);      
    }


    @Override
    public PostDTO update(PostDTO dto) {
        postFileRepository.deleteByPostId(dto.getPostId());
        return save(dto);

    }


    @Override
    public PostDTO getOne(Long postId) {
        Posts post = postRepository.findById(postId).get();
        PostDTO dto = modelMapper.map(post, PostDTO.class);
        return dto;
    }

}
