package com.snackchat.model.dto.user;


import com.snackchat.model.member.Member;
import com.snackchat.util.DateUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {
	private String id;
	
	private String email;
	
	private String userName;
	
	private String password;
	
	private String role;
	
	private String roleId;
	
	private String createdDate;
	
	public UserDto(Member user) {
		this.id = user.getId().toString();
		this.email = user.getEmail();
		this.userName = user.getUserName();
		this.role = user.getRole().getValue();
		this.roleId = user.getRole().name();
		this.createdDate = DateUtil.dateTimeToStringDate(user.getCreatedDate()) ;
	}
}