package com.snackchat.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snackchat.exception.ValidateException;
import com.snackchat.model.dto.member.MemberDto;
import com.snackchat.model.dto.member.SessionDto;
import com.snackchat.model.member.Member;
import com.snackchat.model.member.MemberRepository;
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

//	private void validator(UserDto dto) throws DuplicateException {
//		checkEmail(dto.getEmail());
//		checkPassword(dto.getPassword());
//	}
//
//	private void checkEmail(String email) throws DuplicateException {
//		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
//		Pattern p = Pattern.compile(regex);
//		Matcher m = p.matcher(email);
//		if (!m.matches()) {
//			throw new DuplicateException("이메일이 형식이 휴효하지 않습니다");
//		}
//	}
//
//	private void checkPassword(String password) throws DuplicateException {
//		if (password.length() < 4) {
//			throw new DuplicateException("비밀번호는 4자 이상 입력해야 합니다");
//		}
//	}
//
//	private void checkDuplicateEmailAndName(String email, String userName) throws DuplicateException {
//		Member duplicate = memberRepository.findByEmailOrUserName(email, userName);
//		if (duplicate != null)
//			throw new DuplicateException(email, userName);
//	}
//
//	private void checkDuplicateEmailAndName(MemberDto dto) throws DuplicateException {
//		Member duplicate = memberRepository.findByEmailOrUserName(dto.getEmail(), dto.getUserName());
//		if (duplicate != null) {
//			if (!duplicate.getId().toString().equals(dto.getId()))
//				throw new DuplicateException(dto.getEmail(), dto.getUserName());
//		}
//	}
//
//	@Transactional(readOnly = true)
//	public List<UserDto> getUserList() {
//		List<UserDto> result = memberRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
//		return result;
//	}
}