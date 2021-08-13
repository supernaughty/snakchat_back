package com.snackchat.model.member;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.snackchat.model.BaseEntity;
import com.snackchat.model.dto.member.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID member_id;

	private String name;

	private String email;

	private String password;

	private String phone;

	@Enumerated(EnumType.STRING)
	private ROLE role;

	@Builder
	public Member(String email, String name, String password, String phone, ROLE role, LocalDateTime createDate,
			Member createMember) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.role = role;
		this.createdDate = createDate;
		this.createMember = createMember;
	}

	public void modify(MemberDto dto) {
		this.name = dto.getName();
		this.password = dto.getPassword();
	}

	public void changePassword(String password) {
		this.password = password;
	}
}
