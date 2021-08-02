package com.snackchat.service;

public class DuplicateException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateException(String email, String name) {
		super("������ �̸��� Ȥ�� �̸��� �����մϴ� : " + email + "/" + name);
	}

	public DuplicateException(String err) {
		super(err);
	}
}