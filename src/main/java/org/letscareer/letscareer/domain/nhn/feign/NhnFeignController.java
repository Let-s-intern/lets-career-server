package org.letscareer.letscareer.domain.nhn.feign;

import org.letscareer.letscareer.domain.nhn.dto.request.CreateMessageRequestDto;
import org.letscareer.letscareer.domain.nhn.dto.response.CreateMessageResponseDto;
import org.letscareer.letscareer.global.config.NhnFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "nhn-feign-client", url = "https://api-alimtalk.cloud.toast.com", configuration = NhnFeignClientConfig.class)
public interface NhnFeignController {
    @PostMapping("/alimtalk/v2.3/appkeys/{appkey}/messages")
    CreateMessageResponseDto createPayments(@PathVariable final String appkey, @RequestBody final CreateMessageRequestDto requestDto);
}
