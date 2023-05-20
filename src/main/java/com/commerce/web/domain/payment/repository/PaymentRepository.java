package com.commerce.web.domain.payment.repository;

import com.commerce.db.entity.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {


}
