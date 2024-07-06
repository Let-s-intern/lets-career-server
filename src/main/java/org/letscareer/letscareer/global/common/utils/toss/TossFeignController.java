package org.letscareer.letscareer.global.common.utils.toss;

import org.letscareer.letscareer.global.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "toss-feign-client", url = "https://api.tosspayments.com/v1/payments", configuration = FeignClientConfig.class)
public interface TossFeignController {
    @PostMapping("/confirm")
    String createPayments();
}
