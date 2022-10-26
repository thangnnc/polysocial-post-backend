package com.polysocial.service.impl;

import java.io.IOException;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.polysocial.dto.PostDTO;
import com.polysocial.dto.PostFileUploadDTO;
import com.polysocial.entity.Files;
import com.polysocial.entity.PostFile;
import com.polysocial.exception.PolySocialErrorCode;
import com.polysocial.exception.PolySocialException;
import com.polysocial.repository.PostFileRepository;
import com.polysocial.service.PostFileService;

@Service
public class PostFileServiceImpl implements PostFileService {

    @Autowired
    PostFileRepository postFileRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostFileUploadDTO saveFile(MultipartFile fi) throws PolySocialException {
        Files file = new Files();
        file.setFile(fi);
        String type = checkType(file.getFile().getContentType());
//        PostFileUploadDTO dto = new PostFileUploadDTO();
//        PostFile postFile = new PostFile();
        try {

            String demo = "" + this.cloudinary.uploader().upload(file.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            if (type.equals("video")) {
                int firtsIndex = demo.lastIndexOf("url=");
                int lastIndex = demo.indexOf("tags=");
                String url = demo.substring(firtsIndex + 4, lastIndex - 2);
                System.out.println(url);
//                dto.setUrl(url);
            } else {
                int firtsIndex = demo.indexOf("url=");
                int lastIndex = demo.indexOf("created_at");
                String url = demo.substring(firtsIndex + 4, lastIndex - 2);
                System.out.println(url);
//                dto.setUrl(url);
            }
//            postFile.setPostId(dto.getPostId());
//            postFileRepository.save(postFile);
//            dto.setType(type);
//            System.out.println("dto-->"+dto);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            throw new PolySocialException(PolySocialErrorCode.GENERAL);
        }
    }

    public String checkType(String type) {
        if (type.contains("pdf")) {
            type = "pdf";
        } else if (type.contains("document") || type.contains("msword")) {
            type = "docx";
        } else if (type.contains("zip")) {
            type = "zip";
        } else if (type.contains("jpg") || type.contains("jpeg")) {
            type = "jpg";
        } else if (type.contains("excel")) {
            type = "excel";
        } else if (type.contains("mp4") || type.contains("mp3")) {
            type = "video";
        } else {
            type = "N/A";
        }
        return type;
    }
}
