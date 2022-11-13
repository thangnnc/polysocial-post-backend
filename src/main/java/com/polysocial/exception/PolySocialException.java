package com.polysocial.exception;

/**
 * The SmartHomeException wraps all checked standard Java exception and enriches
 * them with a custom error code. You can use this code to retrieve localized
 * error messages.
 */
public class PolySocialException extends Exception {

	private static final long serialVersionUID = 1L;

	private PolySocialErrorCode error;

	public PolySocialException() {
		super();
	}

	public PolySocialException(PolySocialErrorCode error) {
		super(error.getErrorMessage());
		this.error = error;
	}

	public PolySocialException(Throwable cause, PolySocialErrorCode error) {
		super(cause);
		this.error = error;
	}

	public PolySocialException(PolySocialException e) {
		super(e.error.getErrorMessage());
		this.error = e.error;
	}

	public int getErrorCode() {
		return error.getErrorCode();
	}

	public String getErrorMessage() {
		return error.getErrorMessage();
	}

}
