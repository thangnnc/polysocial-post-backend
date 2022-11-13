package com.polysocial.entity;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
    
    private MultipartFile fi;

    public MultipartFile getFile() {
        return fi;
    }

    public void setFile(MultipartFile fi) {
        this.fi = fi;
    }
}
