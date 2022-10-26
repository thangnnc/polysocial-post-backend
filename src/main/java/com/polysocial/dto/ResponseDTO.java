package com.polysocial.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResponseDTO<T> {
    private int code;

    private String message;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseDTO() {

    }

    @JsonIgnore
    public static <T> ResponseDTO<T> responseSuccess(String message, T data) {
        ResponseDTO result = new ResponseDTO();
        result.code = 1;
        result.message = message;
        result.data = data;
        return result;
    }

    @JsonIgnore
    public static <T> ResponseDTO<T> responseFail(String message) {
        ResponseDTO result = new ResponseDTO();
        result.code = -1;
        result.message = message;
        result.data = null;
        return result;
    }

    @JsonIgnore
    public static <T> ResponseDTO<T> responseUnauthorized(String message) {
        ResponseDTO result = new ResponseDTO();
        result.code = 401;
        result.message = message;
        result.data = null;
        return result;
    }

    @JsonIgnore
    public static <T> ResponseDTO<T> responseForbidden(String message) {
        ResponseDTO result = new ResponseDTO();
        result.code = 403;
        result.message = message;
        result.data = null;
        return result;
    }
}