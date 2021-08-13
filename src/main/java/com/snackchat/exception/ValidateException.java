package com.snackchat.exception;

public class ValidateException extends Exception {
	private static final long serialVersionUID = 1L;

	public ValidateException(String email, String name) {
		super("������ �̸��� Ȥ�� �̸��� �����մϴ� : " + email + "/" + name);
	}

	public ValidateException(String err) {
		super(err);
	}
}