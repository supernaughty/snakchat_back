package com.snackchat;

import java.text.ParseException;
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
class DateTests {
	@Test
	public void compareDate1() throws ParseException {

		LocalDateTime date1 = LocalDateTime.parse("2019-07-12T10:11:50");
		LocalDateTime date2 = LocalDateTime.parse("2019-07-12T10:11:50");
		date2.plusMinutes(15);
		
		System.out.println(date2);

		if (date1.isBefore(date2)) {
			System.out.println("Date1 is before Date2");
		}

		if (date1.isAfter(date2)) {
			System.out.println("Date1 is after Date2");
		}

		if (date1.isEqual(date2)) {
			System.out.println("Date1 is equal Date2");
		}
	}

}
