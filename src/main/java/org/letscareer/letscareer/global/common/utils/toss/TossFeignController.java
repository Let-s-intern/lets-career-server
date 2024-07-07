package org.letscareer.letscareer.global.common.utils.toss;

import org.letscareer.letscareer.domain.pg.dto.request.TossPaymentsRequestDto;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.global.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "toss-feign-client", url = "https://api.tosspayments.com/v1/payments", configuration = FeignClientConfig.class)
public interface TossFeignController {
    @PostMapping("/confirm")
    TossPaymentsResponseDto createPayments(@RequestBody final TossPaymentsRequestDto requestDto);
}
