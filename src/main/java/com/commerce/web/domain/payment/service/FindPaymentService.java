package com.commerce.web.domain.payment.service;

import com.commerce.db.entity.payment.Payment;
import com.commerce.web.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindPaymentService {

    private final PaymentRepository paymentRepository;

    public Payment findPaymentByIdOrElseThrow(Long paymentId) {

        return paymentRepository.findById(paymentId).orElseThrow();


    }

}
