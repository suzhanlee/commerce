package com.commerce.web.domain.member.repository;

import com.commerce.db.entity.member.MemberSignUpLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSignUpLogRepository extends JpaRepository<MemberSignUpLog, Long> {

}
