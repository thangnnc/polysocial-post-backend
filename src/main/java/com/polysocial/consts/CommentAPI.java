package com.polysocial.consts;

import org.springframework.stereotype.Component;



@Component
public class CommentAPI {

    public static final String API_GET_COMMENT = "/api/comment";

    public static final String API_PUT_COMMENT = "/api/comment/{id}";

    public static final String API_DELETE_COMMENT = "/api/comment/delete/{id}";
}
