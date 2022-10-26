package com.polysocial.service;

import org.springframework.web.multipart.MultipartFile;

import com.polysocial.dto.PostDTO;
import com.polysocial.dto.PostFileUploadDTO;
import com.polysocial.exception.PolySocialException;

public interface PostFileService {

    PostFileUploadDTO saveFile(MultipartFile fi) throws PolySocialException;


}
