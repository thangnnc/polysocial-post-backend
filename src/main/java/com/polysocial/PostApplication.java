package com.polysocial;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cloudinary.Cloudinary;

@SpringBootApplication
public class PostApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Cloudinary cloudinaryConfig() {
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", "dwc7dkxy7");
        config.put("api_key", "914855124788275");
        config.put("api_secret", "au9oMdvudygCWWn__i1jRKtvvvs");
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }

    public static void main(String[] args) {
        SpringApplication.run(PostApplication.class, args);
    }

}
