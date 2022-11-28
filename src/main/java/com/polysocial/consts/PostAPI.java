package com.polysocial.consts;

import org.springframework.stereotype.Component;



@Component
public class PostAPI {

    public static final String API_GET_POST = "/api/post";

    public static final String API_UPDATE_POST = "/api/post/update";
    
    public static final String API_UPLOADFILE_POST = "/api/upload";

    public static final String API_DELETE_POST = "/api/post/delete";

    public static final String API_GET_ONE_POST = "/api/get-post";

    public static final String API_GET_POST_BY_GROUP = "/api/get-post-by-group";
}