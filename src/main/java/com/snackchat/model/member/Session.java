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
	
	private LocalDateTime login_time;
	private LocalDateTime last_action_time;
	private LocalDateTime logout_time;
	
	public Session(Member member, LocalDateTime loginTime) {
		this.member = member;
		this.login_time = loginTime;
	}
	
	public void logout(LocalDateTime logoutTime) {
		this.logout_time = logoutTime;
	}

}
