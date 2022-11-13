package com.polysocial.exception;

public enum PolySocialErrorCode {

    GENERAL(2, "Lỗi: có lỗi xảy ra"),
    ERROR_MSG_POST_ID_NOT_FOUND(3, "Lỗi: không tìm thấy id bài viết "),
    ERROR_MSG_LIKE_ID_NOT_FOUND(4, "Lỗi: không tìm thấy id like ");

	private final int errorCode;
	private final String errorMessage;
	
	private PolySocialErrorCode(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
