package com.snackchat.exception;

public class NoSearchObjectException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoSearchObjectException(String error) {
		super(error + "��(��) �������� �ʽ��ϴ�");
	}
}
