package com.snackchat.model.member;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member, UUID> {
	Optional<Member> findByUserName(String userName);

	Optional<Member> findByEmail(String email);

	Member findByEmailOrUserName(String email, String userName);
	Member findByEmailOrUserNameAndIdNot(String email, String usrName, Long id);
}
