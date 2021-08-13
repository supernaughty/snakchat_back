package com.snackchat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snackchat.exception.ValidateException;
import com.snackchat.model.dto.member.MemberDto;
import com.snackchat.model.dto.member.SessionDto;
import com.snackchat.service.Memberervice;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {
	private final Memberervice memberService;

	@PostMapping("/authenticate")
	public ResponseEntity<SessionDto> login(@RequestBody MemberDto dto) throws ValidateException {
		SessionDto sessionDto = memberService.login(dto);
		return ResponseEntity.ok().body(sessionDto);
	}
}
