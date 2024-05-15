package org.letscareer.letscareer.domain.payment.repository;

import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
