package com.snackchat.model.dto.member;


import com.snackchat.model.member.Member;
import com.snackchat.util.DateUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDto {
	
	private String email;
	
	private String name;
	
	private String phone;
	
	private String password;
	
	private String role;
	
	private String createdDate;
	
	private String createUser;
	
	private String modifyUser;
	
	private String modifyDate;
	
	public MemberDto(Member user) {
		this.email = user.getEmail();
		this.name = user.getName();
		this.phone = user.getPhone();
		this.role = user.getRole().getValue();
		this.createdDate = DateUtil.dateTimeToStringDate(user.getCreatedDate());
		this.createUser = user.getCreateMember().getName();
	}
}