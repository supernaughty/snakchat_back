package com.snackchat.exception;

public class DuplicateException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateException(String error) {
		super("�̹� ���� �մϴ� : " + error);
	}
}
