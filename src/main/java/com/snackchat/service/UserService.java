package com.snackchat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snackchat.model.dto.user.UserDto;
import com.snackchat.model.member.UserRepository;

import lombok.RequiredArgsConstructor;

@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository memberRepository;

	private void validator(UserDto dto) throws DuplicateException {
		checkEmail(dto.getEmail());
		checkPassword(dto.getPassword());
	}

	private void checkEmail(String email) throws DuplicateException {
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new DuplicateException("이메일이 형식이 휴효하지 않습니다");
		}
	}

	private void checkPassword(String password) throws DuplicateException {
		if (password.length() < 4) {
			throw new DuplicateException("비밀번호는 4자 이상 입력해야 합니다");
		}
	}

	private void checkDuplicateEmailAndName(String email, String userName) throws DuplicateException {
		Member duplicate = memberRepository.findByEmailOrUserName(email, userName);
		if (duplicate != null)
			throw new DuplicateException(email, userName);
	}

	private void checkDuplicateEmailAndName(MemberDto dto) throws DuplicateException {
		Member duplicate = memberRepository.findByEmailOrUserName(dto.getEmail(), dto.getUserName());
		if (duplicate != null) {
			if (!duplicate.getId().toString().equals(dto.getId()))
				throw new DuplicateException(dto.getEmail(), dto.getUserName());
		}
	}

	@Transactional(readOnly = true)
	public List<UserDto> getUserList() {
		List<UserDto> result = memberRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
		return result;
	}
}