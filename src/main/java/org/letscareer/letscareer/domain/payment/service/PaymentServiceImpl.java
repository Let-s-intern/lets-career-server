package org.letscareer.letscareer.domain.payment.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentHelper paymentHelper;
}
