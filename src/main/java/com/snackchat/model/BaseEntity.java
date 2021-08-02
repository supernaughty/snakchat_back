package com.snackchat.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.snackchat.model.member.Member;


public class BaseEntity {
	@CreatedDate
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;
	
	@CreatedBy
	private Member createMember;
	
	@LastModifiedBy
	private Member modifiedMember;
}
