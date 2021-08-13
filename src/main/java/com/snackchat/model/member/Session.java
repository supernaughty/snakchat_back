package com.snackchat.model.member;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "session")
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID session_id;

	@ManyToOne
	@JoinColumn(name = "member")
	private Member member;

	@Column(name = "create_time")
	private LocalDateTime createTime;
	
	@Column(name = "last_access_time")
	private LocalDateTime lastAccessTime;
	
	@Column(name = "logout_time")
	private LocalDateTime logoutTime;

	@Builder
	public Session(Member member, LocalDateTime createTime) {
		this.member = member;
		this.createTime = createTime;
		this.lastAccessTime = createTime;
	}

	public void logout(LocalDateTime logoutTime) {
		this.logoutTime = logoutTime;
	}
}
