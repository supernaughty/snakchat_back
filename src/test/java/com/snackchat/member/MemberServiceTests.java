package com.snackchat.member;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.snackchat.exception.NoSearchObjectException;
import com.snackchat.model.dto.member.MemberDto;
import com.snackchat.model.member.Member;
import com.snackchat.model.member.MemberRepository;
import com.snackchat.model.member.ROLE;
import com.snackchat.model.member.Session;
import com.snackchat.model.member.SessionRepository;

@SpringBootTest
class MemberServiceTests {

	private final String MAX_ACCESS_TIME = "60"; // 1분

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Test
	void createRoot() {
		String email = "root@daum.net";
		String name = "root";
		String phone = "01012343214";
		String password = "root";
		ROLE role = ROLE.ADMIN;

		Member member = Member.builder().email(email).name(name).password(password).phone(phone).role(role)
				.createDate(LocalDateTime.now()).createMember(null).build();
		memberRepository.save(member);
	}

	@Test
	void login() {
		String loginEmail = "test@daum.net";
		String password = "changepassword";
		try {
			Member member = memberRepository.findByEmailAndPassword(loginEmail, password)
					.orElseThrow(() -> new Exception("다시 입력 해주세요"));

			Session session = Session.builder().member(member).createTime(LocalDateTime.now()).build();
			sessionRepository.save(session);

//			return session;
		} catch (Exception e) {
			System.out.println(e.getMessage());
//			return null;
		}
	}

	@Test
	void logout() {
		try {
			String uid = "c2a4eac8-e82e-4c2d-8829-92cf3ac1554a";

			UUID sessionId = UUID.fromString(uid);
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new NoSearchObjectException("세션"));
			
			session.logout(LocalDateTime.now());
			sessionRepository.save(session);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	Member findMemberByEmail(String email) throws NoSearchObjectException {
		Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoSearchObjectException(email));
		return member;
	}

	@Test
	void createUser() {
		try {
			Member createMember = findMemberByEmail("root@daum.net");
			String email = "test@daum.net";
			String name = "testUser";
			String password = "test1234";
			String phone = "01022223333";
			ROLE role = ROLE.USER;

			Member member = Member.builder().email(email).name(name).password(password).phone(phone).role(role)
					.createDate(LocalDateTime.now()).createMember(createMember).build();
			memberRepository.save(member);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	void modifyUser() {
		try {
			MemberDto dto = new MemberDto();
			dto.setName("testUser");
			dto.setPassword("changepassword");

			Member member = findMemberByEmail("test@daum.net");
			member.modify(dto);
			memberRepository.save(member);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	void deleteUser() {
		try {
			Member member = findMemberByEmail("test@daum.net");
			memberRepository.delete(member);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
