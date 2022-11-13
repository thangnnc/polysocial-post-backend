package com.polysocial.consts;

import org.springframework.stereotype.Component;



@Component
public class PostAPI {

    public static final String API_GET_POST = "/api/post";

    public static final String API_PUT_DELETE = "/api/post/{id}";
    
    public static final String API_UPLOADFILE_POST = "/api/upload";

}
