package com.polysocial.service.impl;

import com.polysocial.dto.CommentResponseDTO;
import com.polysocial.dto.LikeResponseDTO;
import com.polysocial.dto.ListPostDTO;
import com.polysocial.dto.PostDTO;
import com.polysocial.dto.PostFileDTO;
import com.polysocial.dto.PostFileResponseDTO;
import com.polysocial.dto.PostResponseDTO;
import com.polysocial.entity.Comments;
import com.polysocial.entity.Likes;
import com.polysocial.entity.PostFile;
import com.polysocial.entity.Posts;
import com.polysocial.repository.CommentRepository;
import com.polysocial.repository.LikeRepository;
import com.polysocial.repository.PostFileRepository;
import com.polysocial.repository.PostRepository;
import com.polysocial.repository.UserRepo;
import com.polysocial.service.PostService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

<<<<<<< Updated upstream
	@Override
=======
    @Autowired
    private UserRepo userRepo;

    @Override
>>>>>>> Stashed changes
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
				List<Object[]> l = likeRepository.findByPostId(post.getPostId());
				PostResponseDTO dto = modelMapper.map(post, PostResponseDTO.class);

				List<LikeResponseDTO> listResponseDTO = new ArrayList<>();
				for (Object[] objects : l) {
					LikeResponseDTO likeResponse = new LikeResponseDTO();
					likeResponse.setStudentCode(objects[0].toString());
					likeResponse.setPostId(Long.parseLong(objects[1].toString()));
					likeResponse.setStatus(Boolean.parseBoolean(objects[2].toString()));
					listResponseDTO.add(likeResponse);
				}
				
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
				dto.setListLike(listResponseDTO);
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
			for (int i = 0; i < dto.getListPath().size(); i++) {
				String type = dto.getListPath().get(i).substring(dto.getListPath().get(i).lastIndexOf(".") + 1);
				if (type.contains("jpg") || type.contains("png") || type.contains("jpeg") || type.contains("gif")) {
					type = "img";
				}
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
			if (postById.isPresent()) {
				throw new Exception();
			}
			PostDTO dto = modelMapper.map(postById, PostDTO.class);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
<<<<<<< Updated upstream
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

	@Override
	public ListPostDTO findAllPageByGroup(Long groupId, Integer page, Integer limit) {
		ListPostDTO listPostDTO = new ListPostDTO();
		Pageable pageable = PageRequest.of(page, limit);
		Page<Posts> pagePost = this.postRepository.findAllDESCGroup(groupId, pageable);
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
				try {
					for (int i = 0; i < listUrl.size(); i++) {
						dto.getListUrl().get(i)
								.setType(postFileRepository.findByPostId(post.getPostId()).get(i).getType());
					}
				} catch (Exception e) {

				}
				listPostConver.add(dto);

			}
			listPostDTO.setListPostDTO(listPostConver);

		}
		listPostDTO.setTotalPage((int) Math.ceil((double) (pagePost.getTotalElements()) / limit));
		listPostDTO.setTotalItem((int) pagePost.getTotalElements());
		listPostDTO.setPage(page);
		return listPostDTO;
	}
=======
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


    @Override
    public ListPostDTO findAllPageByGroup(Long groupId, Integer page, Integer limit) {
        ListPostDTO listPostDTO = new ListPostDTO();
        Pageable pageable = PageRequest.of(page, limit);
        Page<Posts> pagePost = this.postRepository.findAllDESCGroup(groupId,pageable);
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
                List<Likes> listLike = likeRepository.findPostByPostId(post.getPostId());
                List<LikeResponseDTO> listResponseDTO = listLike.stream().map(item -> modelMapper.map(item, LikeResponseDTO.class)).collect(Collectors.toList());
                for (LikeResponseDTO likeResponseDTO : listResponseDTO) {
                    likeResponseDTO.setStudentCode(userRepo.findById(likeResponseDTO.getUserId()).get().getStudentCode());
                }
                dto.setListLike(listResponseDTO);
                try{
                   for(int i = 0 ; i < listUrl.size() ;i++){
                      dto.getListUrl().get(i).setType(postFileRepository.findByPostId(post.getPostId()).get(i).getType());
                   }
                }catch(Exception e){

                }
                listPostConver.add(dto);

            }
            listPostDTO.setListPostDTO(listPostConver);

        }
        listPostDTO.setTotalPage((int) Math.ceil((double) (pagePost.getTotalElements()) / limit));
        listPostDTO.setTotalItem((int) pagePost.getTotalElements());
        listPostDTO.setPage(page);
        return listPostDTO;
    }
>>>>>>> Stashed changes

}