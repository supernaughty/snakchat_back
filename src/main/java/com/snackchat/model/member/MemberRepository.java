package com.snackchat.model.member;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, UUID> {
	Optional<Member> findByEmail(String email);

	Optional<Member> findByEmailAndPassword(String email, String password);
}
