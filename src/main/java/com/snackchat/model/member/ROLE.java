package com.snackchat.model.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ROLE {
	ADMIN("������"), USER("���");

	private String value;

}
