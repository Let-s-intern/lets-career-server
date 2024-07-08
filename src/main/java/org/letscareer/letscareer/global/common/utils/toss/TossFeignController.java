package org.letscareer.letscareer.global.common.utils.toss;

import org.letscareer.letscareer.domain.pg.dto.request.TossPaymentsRequestDto;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.global.config.TossFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "toss-feign-client", url = "https://api.tosspayments.com/v1/payments", configuration = TossFeignClientConfig.class)
public interface TossFeignController {
    @PostMapping("/confirm")
    TossPaymentsResponseDto createPayments(@RequestBody final TossPaymentsRequestDto requestDto);

    @GetMapping("/{paymentKey}")
    TossPaymentsResponseDto getPaymentDetail(@PathVariable final String paymentKey);
}
