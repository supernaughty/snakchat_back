package com.snackchat.service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snackchat.exception.ValidateException;
import com.snackchat.model.dto.member.MemberDto;
import com.snackchat.model.dto.member.SessionDto;
import com.snackchat.model.member.Member;
import com.snackchat.model.member.MemberRepository;
import com.snackchat.model.member.ROLE;
import com.snackchat.model.member.Session;
import com.snackchat.model.member.SessionRepository;

import lombok.RequiredArgsConstructor;

@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Service
public class Memberervice {
	private final MemberRepository memberRepository;
	private final SessionRepository sessionRepository;

	public SessionDto login(MemberDto dto) throws ValidateException {
		Member member = memberRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
				.orElseThrow(() -> new ValidateException("다시 입력 해주세요"));

		Session session = Session.builder().member(member).createTime(LocalDateTime.now()).build();
		sessionRepository.save(session);

		SessionDto sessionDto = new SessionDto(session.getSession_id().toString(), member.getMember_id().toString(),
				member.getName());
		return sessionDto;
	}

	public void register(String session, MemberDto dto) throws DuplicateException {
		Session ownerSession = sessionRepository.findById(UUID.fromString(session)).get();
		Member createMember = ownerSession.getMember();
		validator(dto.getEmail());
		Member member = Member.builder().email(dto.getEmail()).name(dto.getName()).password(dto.getPassword())
				.phone(dto.getPhone()).role(ROLE.USER).createDate(LocalDateTime.now()).createMember(createMember)
				.build();
		memberRepository.save(member);
	}

	private void validator(String email) throws DuplicateException {
		checkEmailPattern(email);
		checkDuplicateEmailAndName(email);

	}

	private void checkEmailPattern(String email) throws DuplicateException {
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new DuplicateException("이메일이 형식이 휴효하지 않습니다");
		}
	}

	private void checkDuplicateEmailAndName(String email) throws DuplicateException {
		Member duplicate = memberRepository.findByEmail(email).get();
		if (duplicate != null)
			throw new DuplicateException(email + "이(가) 이미 존재합니다");
	}

//	@Transactional(readOnly = true)
//	public List<UserDto> getUserList() {
//		List<UserDto> result = memberRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
//		return result;
//	}
}