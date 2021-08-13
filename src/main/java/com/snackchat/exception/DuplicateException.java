package com.snackchat.exception;

public class DuplicateException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateException(String error) {
		super("이미 존재 합니다 : " + error);
	}
}
