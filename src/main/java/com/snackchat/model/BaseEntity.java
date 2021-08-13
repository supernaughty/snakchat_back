package com.snackchat.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.snackchat.model.member.Member;

import lombok.Getter;


@Getter
@MappedSuperclass
public class BaseEntity {
	
	@Column(name = "create_date")
	protected LocalDateTime createdDate;
	
	@Column(name = "modify_date")
	protected LocalDateTime modifiedDate;
	
	@OneToOne
	@JoinColumn(name="create_member")
	protected Member createMember;
	
	@OneToOne
	@JoinColumn(name="modify_member")
	protected Member modifiedMember;
}
