package org.letscareer.letscareer.global.common.utils.toss;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthRequestInterceptor implements RequestInterceptor {
    private final TossSecretKeyGenerator tossSecretKeyGenerator;

    @Override
    public void apply(RequestTemplate template) {
        String authorization = tossSecretKeyGenerator.generateSecretKey();
        template.header("Authorization", authorization);
        template.header("Content-Type", "application/json");
    }
}
